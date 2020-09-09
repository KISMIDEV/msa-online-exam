<template>
  <div class="footer">
    <div class="container">
      <div class="site-info">
      </div>
      <div class="line"></div>

    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
    }
  },
  methods: {
    openLayer () {
      this.$notify.info({
        title: '저작권',
        message: '학습용입니다.',
        offset: 70
      })
    },
    openPrivacy () {
      this.$notify.info({
        title: '개인 정보 정책',
        message: '이 웹 사이트는 귀하의 정보를 수집하고 사용하기 위해 관련 법률 및 규정과이 개인 정보 보호 정책의 내용을 엄격하게 준수합니다.',
        offset: 70
      })
    }
  },
  components: {}
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .footer {
    background-position: 50%;
    background-color: #252525;
    width: 100%;
    box-sizing: border-box;
    height: 400px;
  }
  .container {
    width: 1150px;
    margin: 0 auto;
    position: relative;
    z-index: 1;
  }
  .c5 {
    color: #646464;
    right: -70px;
    position: relative;
  }

  .copyright {
    color: #434d55;
    font-size: 12px;
    padding: 20px 0 0;
    display: flex;
  }

  .privacy {
    float: left;
    margin: 0 0 0 12px;
  }

  .content-c0 {
    color: #5079d9;
    cursor: pointer;
    text-decoration: none;
    &:hover {
      color: #3A5FCD;
    }
  }

  .content-c1 {
    float: left;
    line-height: 12px;
    padding: 1px 10px 0;
    border-left: 1px solid #ccc;
    list-style: none;
  }

  .content-c2 {
    float: left;
    height: 15px;
    line-height: 15px;
    color: #ffffffa6;
    margin: 0 0 0 12px;
  }

  .cop {
    text-align: center;
    height: 15px;
  }

  .content-c3 {
    margin-right: 20px;
    color: #ffffffa6;
    font-size: 12px;
    height: 12px;
    line-height: 1;
  }

  .footer-col {
    width: 200px;
    float: left;
    margin-top: 30px;
    h4 {
      color: #fff;
      font-size: 30px;
      font-weight: 400;
    }
    a {
      display: block;
      margin: 0;
      line-height: 2.5;
      font-size: 20px;
      font-weight: 400;
      color: #ffffffa6;
    }
    .we-chat {
      position: absolute;
      right: 0;
      width: 216px;
      z-index: 2;
      padding-top: 260px;
      background-image: url("../../../static/img/core-img/qq.png");
      background-repeat: no-repeat;
      background-position: top;
      text-align: center;
      line-height: 22px;
    }

    .we-chat-new {
      position: absolute;
      width: 216px;
      z-index: 2;
      padding-top: 260px;
      color: #ffffff;
      background-image: url("../../../static/img/core-img/qq_new.png");
      background-repeat: no-repeat;
      background-position: top;
      text-align: center;
      line-height: 22px;
    }
  }
</style>
