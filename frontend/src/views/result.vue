<template>
  <div class="page-result">
    <div class="top">
      <p class="title">凭证二维码 · 第{{ info.round }}轮</p>
      <!-- qr code -->
      <canvas id="qrcodeCanvas"></canvas>
    </div>
    <div class="body">
      <p class="title">个人信息</p>
      <div class="info">
        <p>预约编号：{{ info.serial }}</p>
        <p>姓名：{{ info.name }}</p>
        <p>身份证：{{ info.idNumber }}</p>
        <p>手机号：{{ info.phone }}</p>
        <p>预约数量：{{ info.amount }}</p>
      </div>
    </div>
    <div class="bottom">
      <mt-button type="primary" @click="handleReturnHome">返回主页</mt-button>
    </div>
  </div>
</template>

<script>
import qrcode from 'qrcode'

export default {
  name: 'result',
  data() {
    return {
      info: {
        round: 1,
        name: '',
        phone: '',
        amount: 0,
        serial: '',
        idNumber: ''
      }
    }
  },
  methods: {
    genQrCode(text) {
      const $el = document.getElementById('qrcodeCanvas')
      qrcode.toCanvas($el, text, {
        width: 200
      }, function (error) {
        if (error) console.error(error)
        console.log('success!');
      })
    },
    handleReturnHome() {
      this.$router.push('/')
    }
  },
  created() {
    this.info = this.$route.params
  },
  mounted() {
    this.genQrCode(this.info.serial)
  },
}
</script>

<style scoped lang="scss">
  #qrcodeCanvas {
    display: block;
    margin: 0 auto;
  }

  .title {
    position: relative;
    padding-left: 15px;
    font-weight: bold;
    vertical-align: middle;
    &::before {
      content: '';
      position: absolute;
      left: 0;
      height: 100%;
      width: 5px;
      background: lightsalmon;
    }
  }

  .info {
    padding-left: 16px;
  }

  .bottom {
    margin-top: 50px;
    text-align: center;
    button {
      width: 250px;
      letter-spacing: 1px;
    }
  }
</style>