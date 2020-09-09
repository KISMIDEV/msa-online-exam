<template>
  <div>
    <el-row class="hero-area" type="flex" justify="center" align="middle">
      <el-col :span="24">
        <div class="hero-content">
          <h2>교육관리시스템</h2>
          <h4>마이크로서비스아키텍처기반 교육관리시스템</h4>
          <h4>멀티파트너,권한관리,역할관리,시험,연습 등 기능</h4>
          <router-link to="/courses" class="btn clever-btn">시작하기</router-link>
        </div>
      </el-col>
    </el-row>
    <div class="cool-facts-area padding-80-0">
      <el-row type="flex" justify="center" :gutter="50">
        <el-col :span="4">
          <transition name="fade-transform" mode="out-in">
            <div class="single-cool-facts-area mb-80" v-show="showFacts">
              <div class="icon">
                <img src="static/img/core-img/star.png" alt="">
              </div>
              <h2>
                <count-to :start-val="0" :end-val="200" :duration="2600" class="counter"/>
              </h2>
              <h5>사용자수</h5>
            </div>
          </transition>
        </el-col>
        <el-col :span="4">
          <transition name="fade-transform" mode="out-in">
            <div class="single-cool-facts-area mb-80" v-show="showFacts">
              <div class="icon">
                <img src="static/img/core-img/star.png" alt="">
              </div>
              <h2>
                <count-to :start-val="0" :end-val="500" :duration="2600" class="counter"/>
              </h2>
              <h5>시험수</h5>
            </div>
          </transition>
        </el-col>
        <el-col :span="4">
          <transition name="fade-transform" mode="out-in">
            <div class="single-cool-facts-area mb-80" v-show="showFacts">
              <div class="icon">
                <img src="static/img/core-img/star.png" alt="">
              </div>
              <h2>
                <count-to :start-val="0" :end-val="233" :duration="2600" class="counter"/>
              </h2>
              <h5>문제수</h5>
            </div>
          </transition>
        </el-col>
        <el-col :span="4">
          <transition name="fade-transform" mode="out-in">
            <div class="single-cool-facts-area mb-80" v-show="showFacts">
              <div class="icon">
                <img src="static/img/core-img/star.png" alt="">
              </div>
              <h2>
                <count-to :start-val="0" :end-val="300" :duration="2600" class="counter"/>
              </h2>
              <h5>과정수</h5>
            </div>
          </transition>
        </el-col>
      </el-row>
    </div>

    <div class="popular-courses-area padding-80-0">
      <el-row>
        <el-col :span="24">
          <div class="section-heading">
            <h3>가장 핫한 과정</h3>
          </div>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" :gutter="50">
        <el-col :span="6">
          <transition name="fade-transform" mode="out-in">
            <div class="single-popular-course" v-show="showCourses">
              <img src="static/img/bg-img/c1.jpg" alt="">
              <div class="course-content">
                <h4>영어문법</h4>
                <div class="meta d-flex align-items-center">
                  <a href="#">Tanma</a>
                  <span><i class="fa fa-circle" aria-hidden="true"></i></span>
                  <a href="#">예술 &amp; 설</a>
                </div>
                <p>영어 문법을 완성하는 데 7 일이 걸리는 첫 번째 유명한 교사는 기본 영어 문법 딜레마를 극복하고 확고하게 향상시킵니다.</p>
              </div>
              <div class="seat-rating-fee d-flex justify-content-between">
                <div class="seat-rating h-100 d-flex align-items-center">
                  <div class="seat">
                    <i class="el-icon-user-solid" aria-hidden="true"></i> 10
                  </div>
                  <div class="rating">
                    <i class="el-icon-star-on" aria-hidden="true"></i> 4.5
                  </div>
                </div>
                <div class="course-fee h-100">
                  <a href="#" class="free">무료</a>
                </div>
              </div>
            </div>
          </transition>
        </el-col>
        <el-col :span="6">
          <transition name="fade-transform" mode="out-in">
            <div class="single-popular-course mb-80" v-show="showCourses">
              <img src="static/img/bg-img/c2.jpg" alt="">
              <div class="course-content">
                <h4>词汇</h4>
                <div class="meta d-flex align-items-center">
                  <a href="#">Tanma</a>
                  <span><i class="fa fa-circle" aria-hidden="true"></i></span>
                  <a href="#">예술 &amp; 설계 </a>
                </div>
                <p>전문 영어 어휘 교육 과외 수업, 영어 어휘 교육 성과를 효과적으로 향상시키는 데 도움이되는 체계적인 방법</p>
              </div>
              <div class="seat-rating-fee d-flex justify-content-between">
                <div class="seat-rating h-100 d-flex align-items-center">
                  <div class="seat">
                    <i class="el-icon-user-solid" aria-hidden="true"></i> 10
                  </div>
                  <div class="rating">
                    <i class="el-icon-star-on" aria-hidden="true"></i> 4.5
                  </div>
                </div>
                <div class="course-fee h-100">
                  <a href="#">무료</a>
                </div>
              </div>
            </div>
          </transition>
        </el-col>
        <el-col :span="6">
          <transition name="fade-transform" mode="out-in">
            <div class="single-popular-course mb-80" v-show="showCourses">
              <img src="static/img/bg-img/c3.jpg" alt="">
              <div class="course-content">
                <h4>캡션 작성</h4>
                <div class="meta d-flex align-items-center">
                  <a href="#">Tanma</a>
                  <span><i class="fa fa-circle" aria-hidden="true"></i></span>
                  <a href="#">예술 &amp; 설계</a>
                </div>
                <p>학생들이 지침을 읽고 쓰는 능력을 훈련시키기 위해 선택된 튜토리얼</p>
              </div>
              <div class="seat-rating-fee d-flex justify-content-between">
                <div class="seat-rating h-100 d-flex align-items-center">
                  <div class="seat">
                    <i class="el-icon-user-solid" aria-hidden="true"></i> 10
                  </div>
                  <div class="rating">
                    <i class="el-icon-star-on" aria-hidden="true"></i> 4.5
                  </div>
                </div>
                <div class="course-fee h-100">
                  <a href="#">무료</a>
                </div>
              </div>
            </div>
          </transition>
        </el-col>
      </el-row>
    </div>

    <div class="blog-area padding-80-0">
      <el-row>
        <el-col :span="24">
          <div class="section-heading">
            <h3>블로그공유</h3>
          </div>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" :gutter="50">
        <el-col :span="10">
          <transition name="fade-transform" mode="out-in">
            <div class="single-blog-area mb-80" v-show="showBlog">
              <img src="static/img/blog-img/1.jpg" alt="">
              <div class="blog-content">
                <a href="#" class="blog-headline">
                  <h4>영어문법교사</h4>
                </a>
                <div class="meta d-flex align-items-center">
                  <a href="#">Tanma</a>
                  <span><i class="fa fa-circle" aria-hidden="true"></i></span>
                  <a href="#">예술 &amp; 설계</a>
                </div>
                <p>영어 문법을 완성하는 데 7 일이 걸리는 첫 번째 유명한 교사는 기본 영어 문법 딜레마를 극복하고 확고하게 향상시킵니다.</p>
              </div>
            </div>
          </transition>
        </el-col>
        <el-col :span="10">
          <transition name="fade-transform" mode="out-in">
            <div class="single-blog-area mb-80" v-show="showBlog">
              <img src="static/img/blog-img/2.jpg" alt="">
              <div class="blog-content">
                <a href="#" class="blog-headline">
                  <h4>영어문법교수</h4>
                </a>
                <div class="meta d-flex align-items-center">
                  <a href="#">Tanma</a>
                  <span><i class="fa fa-circle" aria-hidden="true"></i></span>
                  <a href="#">예술 &amp; 설계</a>
                </div>
                <p>영어 문법을 완성하는 데 7 일이 걸리는 첫 번째 유명한 교사는 기본 영어 문법 딜레마를 극복하고 확고하게 향상시킵니다.</p>
              </div>
            </div>
          </transition>
        </el-col>
      </el-row>
    </div>
    <div v-if="isActive" class="go-top-box" @click="goTop(step)">
      <i class="top-icon el-icon-caret-top"></i>
    </div>
    <o-footer></o-footer>
  </div>
</template>

<script>
import OFooter from '../common/footer'
import CountTo from 'vue-count-to'

export default {
  props: {
    step: {
      type: Number,
      default: 50
    }
  },
  data () {
    return {
      isActive: false,
      showFacts: false,
      showCourses: false,
      showBlog: false
    }
  },
  components: {
    OFooter,
    CountTo
  },
  methods: {
    // 返回顶部
    goTop: function (i) {
      document.documentElement.scrollTop -= i
      if (document.documentElement.scrollTop > 0) {
        setTimeout(() => this.goTop(i), 16)
      }
    }
  },
  created () {
    let vm = this
    window.onscroll = function () {
      vm.isActive = document.documentElement.scrollTop > 60
      if (document.documentElement.scrollTop > 250) {
        setTimeout(() => {
          vm.showCourses = true
        }, 350)
      }

      if (document.documentElement.scrollTop > 650) {
        setTimeout(() => {
          vm.showBlog = true
        }, 350)
      }
    }
    setTimeout(() => {
      vm.showFacts = true
    }, 350)
  }
}
</script>
