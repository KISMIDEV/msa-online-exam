<template>
  <fixed-header>
    <div class="header-area">
      <div class="clever-main-menu">
        <div class="classy-nav-container breakpoint-off">
          <nav class="classy-navbar justify-content-between" id="cleverNav">
            <a class="nav-brand" href="/">교육관리시스템</a>
            <div class="classy-menu">
              <div class="classynav">
                <div class="search-area">
                  <el-input type="search" prefix-icon="el-icon-search" v-model="query" name="search" id="search" placeholder="검색" @keyup.enter="search()"/>
                </div>
                <el-menu :default-active="activeIndex"
                         mode="horizontal"
                         text-color="rgba(0, 0, 0, 0.45)"
                         active-text-color="#232323"
                         :unique-opened=true
                         @select="handleSelect">
                  <el-menu-item index="/index" @click="open('/home')">홈</el-menu-item>
                  <el-menu-item index="/exams" @click="open('/exams')">시험</el-menu-item>
                  <el-menu-item index="/courses" @click="open('/courses')">과정</el-menu-item>
                  <el-submenu index="/other">
                    <template slot="title">기록</template>
                    <el-menu-item index="exam-record" @click="open('/exam-record')">시험기록</el-menu-item>
                    <el-menu-item index="incorrect" @click="todo">오답문제</el-menu-item>
                  </el-submenu>
                  <el-submenu index="/u">
                    <template slot="title">도움</template>
                    <el-menu-item index="u-source" @click="open('https://gitee.com/wells2333/spring-microservice-exam')">
                      소스코드
                    </el-menu-item>
                    <el-menu-item index="u-deploy" @click="open('https://www.kancloud.cn/kingjava/spring-microservice-exam/1322870')">
                      배포문서
                    </el-menu-item>
                    <el-menu-item index="c-log" @click="open('https://gitee.com/wells2333/spring-microservice-exam/blob/master/CHANGELOG.md')">
                      갱신로그
                    </el-menu-item>
                    <el-menu-item index="c-overview"  @click="open('https://www.kancloud.cn/kingjava/spring-microservice-exam/1322864#6__112')">
                      계획개요
                    </el-menu-item>
                    <el-menu-item index="u-admin" @click="open('http://127.0.0.1:81')">
                      관리자
                    </el-menu-item>
                  </el-submenu>
                  <el-submenu v-if="login" index="/user-info">
                    <template slot="title">
                      <img :src="userInfo.avatarUrl" style="height: 30px;border-radius: 50%;margin-right: 6px;"/>
                      {{userInfo.identifier}}
                    </template>
                    <el-menu-item index="account" @click="open('/account')">프로필</el-menu-item>
                    <el-menu-item index="password" @click="open('/password')">비밀번호변경</el-menu-item>
                    <el-menu-item index="logOut" @click="logOut">로그아웃</el-menu-item>
                  </el-submenu>
                </el-menu>
                <div class="register-login-area" v-if="!login">
                  <a class="btn" target="_blank" @click="open('/register')">회원가입</a>
                  <a class="btn" target="_blank" @click="open('/login')">로그인</a>
                </div>
              </div>
            </div>
          </nav>
        </div>
      </div>
    </div>
  </fixed-header>
</template>

<script>
import { mapState } from 'vuex'
import { isNotEmpty, messageWarn } from '@/utils/util'
import FixedHeader from 'vue-fixed-header'

export default {
  components: {
    FixedHeader
  },
  computed: {
    // 获取用户信息
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  created () {
    this.checkLogin()
  },
  // 检测路由变化
  watch: {
    $route () {
      this.checkLogin()
    }
  },
  data () {
    return {
      activeIndex: '/index',
      login: false,
      input: '',
      query: ''
    }
  },
  methods: {
    // 导航栏切换
    open (path) {
      if (path.startsWith('http')) {
        window.open(path)
        return
      }
      if (path !== this.$route.fullPath) {
        if (this.$route.fullPath === '/start') {
          this.$confirm('是否要结束当前考试?', '경고', {
            confirmButtonText: '확인',
            cancelButtonText: '취소',
            type: 'warning'
          }).then(() => {
            // TODO 提交当前考试
            this.$emit('handleSubmitExam')
            this.$router.push({
              path: path,
              query: {}
            })
          }).catch(() => {})
        } else {
          this.$router.push({
            path: path,
            query: {}
          })
        }
      }
    },
    // 选择事件
    handleSelect (item) {
    },
    // 注册
    handleRegister () {
      // 先退出
      // this.logOut()
      this.$router.push('/register')
    },
    // 로그인
    handleLogin () {
      this.$router.push('/login')
    },
    // 登出
    logOut () {
      this.$store.dispatch('LogOut').then(() => {
        this.login = false
        this.$router.push('/home')
      }).catch(() => {
        this.login = false
        this.$router.push('/home')
      })
    },
    // 检测로그인
    checkLogin () {
      if (this.userInfo.id !== undefined) {
        this.login = true
      }
    },
    search () {
      if (isNotEmpty(this.query)) {
        this.$router.push({name: 'exams', query: {query: this.query}})
      }
    },
    todo () {
      messageWarn(this, '功能正在开发中！')
    }
  }
}
</script>
