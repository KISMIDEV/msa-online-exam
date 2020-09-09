<template>
  <div class="bg">
    <!-- 背景 -->
    <svg id="bg_svg" viewBox="0 0 1440 480" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid slice">
      <filter id="blur">
        <feGaussianBlur stdDeviation="5"></feGaussianBlur>
      </filter>
      <image xlink:href="static/img/login/login-bg.jpg" width="1440" height="480" filter="url(#blur)"></image>
    </svg>
    <!-- 注册、로그인表单 -->
    <div class="login-wrap">
      <el-tabs v-model="activeName">
        <!-- 注册 -->
        <el-tab-pane label="등록" name="/register" class="login-wrap-title">
          <el-form ref="registerForm" :model="register.form" :rules="register.rules" class="register-form" label-position="left" auto-complete="off">
            <el-form-item prop="identifier">
              <el-input placeholder="사용자명" v-model="register.form.identifier" name="identifier" type="text" auto-complete="off"/>
            </el-form-item>
            <el-form-item prop="email">
              <el-input placeholder="이메일주소" v-model="register.form.email" name="email" type="text" auto-complete="off"/>
            </el-form-item>
            <el-form-item prop="credential">
              <el-input placeholder="비밀번호" :type="register.passwordType" v-model="register.form.credential" name="credential" auto-complete="off" @keyup.enter.native="handleRegister"/>
            </el-form-item>
            <el-form-item prop="code">
              <el-row :span="24">
                <el-col :span="14">
                  <el-input :maxlength="register.code.len" v-model="register.form.code" auto-complete="off" placeholder="보안코드를 입력하세요" @keyup.enter.native="handleRegister" />
                </el-col>
                <el-col :span="10">
                  <div class="login-code">
                    <span v-if="register.code.type === 'text'" class="login-code-img" @click="refreshRegisterCode">{{ register.code.value }}</span>
                    <img v-else :src="register.code.src" alt="보안코드" class="login-code-img" @click="refreshRegisterCode">
                  </div>
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item>
              <el-button :loading="register.loading" type="primary" @click.native.prevent="handleRegister">등록</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <!-- 로그인 -->
        <el-tab-pane label="로그인" name="/login" class="login-wrap-title">
          <div v-if="!useSmsLogin">
            <el-form ref="loginForm" :model="login.form" :rules="login.rules" class="login-form" auto-complete="on" label-position="left">
              <el-form-item prop="identifier">
                <el-input placeholder="사용자명 혹은 이메일주소" v-model="login.form.identifier" name="identifier" type="text" auto-complete="on"/>
              </el-form-item>
              <el-form-item prop="credential">
                <el-input placeholder="비밀번호" :type="login.passwordType" v-model="login.form.credential" name="credential" auto-complete="on" @keyup.enter.native="handleLogin"/>
                <span class="forgot-suffix">
                <span class="forgot-link">
                  <router-link to="/reset-password">
                    <span>비밀번호가 생각나지 않으세요?</span>
                  </router-link>
                </span>
              </span>
              </el-form-item>
              <el-form-item prop="code">
                <el-row :span="24">
                  <el-col :span="14">
                    <el-input :maxlength="login.code.len" v-model="login.form.code" auto-complete="off" placeholder="보안코드를 입력하세요" @keyup.enter.native="handleLogin" />
                  </el-col>
                  <el-col :span="10">
                    <div class="login-code">
                      <span v-if="login.code.type === 'text'" class="login-code-img" @click="refreshLoginCode">{{ login.code.value }}</span>
                      <img v-else :src="login.code.src" alt="验证码" class="login-code-img" @click="refreshLoginCode">
                    </div>
                  </el-col>
                </el-row>
              </el-form-item>
              <el-form-item>
                <el-button :loading="login.loading" type="primary" @click.native.prevent="handleLogin">로그인</el-button>
              </el-form-item>
              <div class="sms-login">
                <span @click="smsLogin">문자메시지로그인</span>
              </div>
            </el-form>
          </div>
          <!-- 验证码로그인-->
          <div v-else>
            <el-form ref="smsLoginForm" :model="sms.form" :rules="sms.rules" class="login-form" auto-complete="off" label-position="left">
              <el-form-item prop="phone">
                <el-input placeholder="핸드폰번호" v-model="sms.form.phone" name="phone" type="text" auto-complete="off"/>
              </el-form-item>
              <el-form-item prop="code">
                <el-input class="sms-code-input" placeholder="4자리보안코드" v-model="sms.form.code" name="code" type="text" auto-complete="off"/>
                <el-button class="sms-code-send" @click="handleSendSms" :loading="sms.sending">보안코드보내기</el-button>
              </el-form-item>
              <el-form-item>
                <el-button :loading="sms.loading" type="primary" @click.native.prevent="handleSmsLogin">로그인</el-button>
              </el-form-item>
              <div class="sms-login">
                <span @click="accountLogin">아이디비번로그인</span>
              </div>
            </el-form>
          </div>
          <!-- 第三方로그인 -->
          <div class="third-login">
            <el-row>
              <el-col :span="24" class="third-link">
                <a title="wechat로그인" href="">
                  <span class="wechat"></span>wechat
                </a>
              </el-col>
            </el-row>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { randomLenNum, isNotEmpty, isValidPhone } from '@/utils/util'
import { mapGetters } from 'vuex'
import { getToken, getTenantCode } from '@/utils/auth'
import { checkExist } from '@/api/admin/user'
import { sendSms } from '@/api/admin/mobile'

export default {
  data () {
    let checkRegisterUsername = (rule, value, callback) => {
      if (!isNotEmpty(value)) {
        return callback(new Error('사용자명을 입력하세요'))
      }
      // 检查用户名是否存在
      checkExist(value).then(response => {
        if (isNotEmpty(response.data) && response.data.data) {
          callback(new Error('사용자가 존재하지 않습니다.！'))
        } else {
          callback()
        }
      })
    }
    // 校验手机号
    let validPhone = (rule, value, callback) => {
      if (!value) {
        callback(new Error('핸드폰번호를 입력하세요'))
      } else if (!isValidPhone(value)) {
        callback(new Error('11자리핸드폰번호를 입력하세요'))
      } else {
        callback()
      }
    }
    return {
      useSmsLogin: false,
      activeName: '/login',
      login: {
        form: {
          identifier: '',
          credential: '',
          code: '',
          randomStr: '',
          rememberMe: false
        },
        rules: {
          identifier: [{ required: true, trigger: 'blur', message: '사용자명을 입력하세요' }],
          credential: [
            { required: true, trigger: 'blur', message: '비밀번호를 입력하세요' },
            { min: 6, trigger: 'blur', message: '비밀번호는 최소 6자리입니다.' }],
          code: [
            { required: true, message: '보안코드를 입력하세요', trigger: 'blur' },
            { min: 4, max: 4, message: '보안코드는 4자리입니다.', trigger: 'blur' }
          ]
        },
        loading: false,
        passwordType: 'password',
        code: {
          src: '/api/user/v1/code',
          value: '',
          len: 4,
          type: 'image'
        }
      },
      register: {
        form: {
          identifier: '',
          email: '',
          credential: '',
          code: '',
          randomStr: '',
          rememberMe: false
        },
        rules: {
          identifier: [{ validator: checkRegisterUsername, trigger: 'blur' }],
          email: [{ required: true, trigger: 'blur', message: '이메일주소를 입력하세요' }],
          credential: [
            { required: true, trigger: 'blur', message: '비밀번호를 입력하세요' },
            { min: 6, trigger: 'blur', message: '비밀번호는 최소 6자리입니다.' }],
          code: [
            { required: true, message: '보안코드를 입력하세요', trigger: 'blur' },
            { min: 4, max: 4, message: '보안코드는 4자리입니다.', trigger: 'blur' }
          ]
        },
        loading: false,
        passwordType: 'password',
        code: {
          src: '/api/user/v1/code',
          value: '',
          len: 4,
          type: 'image'
        }
      },
      sms: {
        form: {
          phone: '',
          code: ''
        },
        loading: false,
        sending: false,
        rules: {
          phone: [{ required: true, message: '핸드폰번호를 입력하세요', trigger: 'blur', validator: validPhone }]
        }
      }
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created () {
    this.activeName = this.$route.fullPath
    this.refreshLoginCode()
    this.refreshRegisterCode()
  },
  computed: {
    ...mapGetters(['tagWel'])
  },
  methods: {
    refreshLoginCode () {
      this.login.form.code = ''
      this.login.form.randomStr = randomLenNum(this.login.code.len, true)
      this.login.code.type === 'text'
        ? (this.login.code.value = randomLenNum(this.login.code.len))
        : (this.login.code.src = `/api/user/v1/code/${this.login.form.randomStr}?tenantCode=` + getTenantCode())
    },
    refreshRegisterCode () {
      this.register.form.code = ''
      this.register.form.randomStr = randomLenNum(this.register.code.len, true)
      this.register.code.type === 'text'
        ? (this.register.code.value = randomLenNum(this.register.code.len))
        : (this.register.code.src = `/api/user/v1/code/${this.register.form.randomStr}?tenantCode=` + getTenantCode())
    },
    handleLogin () {
      if (getToken()) {
        // 已经로그인，重定向到首页
        this.$router.push('/home')
      } else {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.login.loading = true
            // 로그인，获取token
            this.$store.dispatch('LoginByUsername', this.login.form).then(() => {
              this.login.loading = false
              // 重定向到首页
              this.$router.push({ path: this.redirect || '/' })
            }).catch(() => {
              this.login.loading = false
              this.refreshLoginCode()
            })
          } else {
            return false
          }
        })
      }
    },
    // 注册
    handleRegister () {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.register.loading = true
          this.$store.dispatch('RegisterByUsername', this.register.form).then(() => {
            this.register.loading = false
            this.$message.success('등록성공！')
            this.$router.push({ path: '/login' })
          }).catch(() => {
            this.register.loading = false
            this.refreshRegisterCode()
          })
        } else {
          return false
        }
      })
    },
    // 账号密码로그인
    accountLogin () {
      this.useSmsLogin = false
    },
    smsLogin () {
      this.useSmsLogin = true
    },
    // 短信验证码로그인
    handleSmsLogin () {
      this.useSmsLogin = true
      // 로그인，获取token
      this.$store.dispatch('LoginBySocial', this.sms.form).then(() => {
        // 重定向到首页
        this.$router.push('/')
      }).catch(() => {})
    },
    // 发送验证码
    handleSendSms () {
      this.$refs.smsLoginForm.validate(valid => {
        if (valid) {
          this.sms.sending = true
          sendSms(this.sms.form.phone).then(response => {
            console.log(response.data)
            this.sms.form.code = ''
            this.$message.warning('보안코드가 성공적으로 전송되었습니다.：' + response.data.msg)
            setTimeout(() => {
              this.sms.sending = false
            }, 500)
          }).catch(error => {
            console.error(error)
            this.sms.sending = false
          })
        } else {
          return false
        }
      })
    },
    openMsg () {
      this.$message.warning('잊어버릴수가 없죠？')
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  #bg_svg {
    position: fixed;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
  }
  .bg {
    height: 100%;
    display: flex;
    justify-content: center;
  }
  .login-wrap {
    width: 330px;
    border-radius: 5px;
    padding: 20px;
    margin: 20px;
    background: #ffffff;
    color: #71767a;
    .el-tabs__item {
      font-size: 20px;
    }
    .el-tabs__nav {
      left: 30%;
    }
    .login-code-img {
      margin-left: 10px;
      width: 110px;
      height: 40px;
    }
    /* 注册로그인 */
    .login-wrap-title {
      color: #71767a;
      margin: 0 15px;
      cursor: pointer;
      line-height: 24px;
      border-bottom: 2px solid transparent;
    }
    .el-form-item {
      margin-bottom: 20px !important;
    }
    h3 {
      text-align: center;
      color: #ebedef;
      margin-top: 0;
      margin-bottom: 5px;
      span {
        color: #20a0ff;
      }
    }
    form {
      margin-top: 25px;
      .el-form-item {
        margin-bottom: 15px;
      }
    }
    a {
      text-decoration: none;
      color: #1f2d3d;
    }
    button {
      width: 100%;
      font-weight: 600;
    }
  }
  .forgot-suffix {
    right: 12px;
    position: absolute;
    top: 50%;
    z-index: 2;
    color: #595959;
    line-height: 0;
    -webkit-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
    .forgot-link {
      font-size: 14px;
      border-left: 1px solid #e8e8e8;
      padding-left: 12px;
      padding-right: 5px;
      a {
        color: #8c8c8c;
      }
    }
  }
  .third-login {
    position: relative;
    margin-top: 20px;
    border-top: 1px solid #e8e8e8;
    padding-top: 20px;
    span.wechat {
      background-position: 0 -41.5px;
    }
  }
  .third-link {
    display: flex;
  }
  .third-login span {
    display: inline-block;
    width: 20px;
    height: 21px;
    background: url("../../../static/img/login/third-login.png") no-repeat;
    background-size: 100%;
    vertical-align: middle;
    margin-right: 5px;
  }
  .sms-code-input {
    width: 55%;
  }
  .sms-code-send {
    width: 40% !important;
    margin-left: 10px;
  }
</style>
