<template>
  <div class="tab-container">
    <div class="filter-container">
      <el-row>
        <el-col :span="20" :offset="2" style="margin-top:10px;">
          <el-card class="box-card">
            <el-form ref="form" :rules="rules" :label-position="labelPosition" :model="userInfo" label-width="100px" style="width: 90%;">
              <el-row>
                <el-col :span="12" :offset="6">
                  <el-form-item label="이전비밀번호：" prop="oldPassword">
                    <el-input v-model="userInfo.oldPassword" auto-complete="off" type="password"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12" :offset="6">
                  <el-form-item label="새로운 비밀 번호：" prop="newPassword">
                    <el-input v-model="userInfo.newPassword" auto-complete="off" type="password"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12" :offset="6">
                  <el-form-item label="새로운비밀번호확인" prop="newPassword1">
                    <el-input v-model="userInfo.newPassword1" auto-complete="off" type="password"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12" :offset="8">
                  <el-form-item>
                    <el-button type="primary" @click="update">저장</el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { updatePassword } from '@/api/admin/user'
import { mapState } from 'vuex'
import { notifySuccess, notifyFail, isNotEmpty } from '@/utils/util'

export default {
  name: 'PersonalPassword',
  components: {},
  data () {
    const validatePass = (rule, value, callback) => {
      if (this.userInfo.oldPassword !== '') {
        if (!isNotEmpty(value)) {
          callback(new Error('새로운 비밀번호를 입력하세요'))
        } else if (value.length < 6) {
          callback(new Error('비밀번호는 최소 6자리입니다.'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    const validatePass1 = (rule, value, callback) => {
      if (this.userInfo.oldPassword !== '') {
        if (!isNotEmpty(value)) {
          callback(new Error('비밀번호를 다시 입력해주세요'))
        } else if (value !== this.userInfo.newPassword) {
          callback(new Error('입력하신 비밀번호가 일치하지 않습니다!'))
        } else {
          callback()
        }
      } else {
        callback()
      }
    }
    return {
      labelPosition: 'right',
      rules: {
        oldPassword: [{ required: true, message: '이전비밀번호를 입력하세요', trigger: 'blur' }],
        newPassword: [{ required: true, validator: validatePass, trigger: 'blur' }],
        newPassword1: [{ required: true, validator: validatePass1, trigger: 'blur' }]
      }
    }
  },
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  methods: {
    update () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          updatePassword(this.userInfo).then(response => {
            if (response.data.data) {
              notifySuccess(this, '수정성공')
              // 修改密码之后强制重新로그인
              if (this.userInfo.newPassword !== '') {
                this.$store.dispatch('LogOut').then(() => {
                  location.reload()
                })
              } else {
                this.$router.push({ path: '/' })
              }
            } else {
              notifyFail(this, response.data.msg)
            }
          }).catch(() => {
            notifyFail(this, '수정실패')
          })
        }
      })
    }
  }
}
</script>

<style scoped>
  .tab-container{
    margin: 30px;
  }
</style>
