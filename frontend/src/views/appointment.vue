<template>
  <div class="page-appointment">
    <div class="info-wrapper">
      <p class="info-line">本轮预约是第 {{ config.id }} 轮</p>
      <p class="info-line">本次预约时间：{{ config.begin }} - {{ config.end || '未设定' }}</p>
      <p class="info-line">本次预约口罩数量：{{ config.totalAmount || '10000' }}</p>
    </div>
    <div class="form-wrapper">
      <form>
        <mt-field label="姓名" v-model="form.name"></mt-field>
        <mt-field @blur.native.capture="checkIdNumber" label="身份证" v-model="form.idNumber" :state="form.legal.idNumber ? '' : 'error'"></mt-field>
        <mt-field @blur.native.capture="checkPhone" label="手机号" v-model="form.phone" type="tel" :state="form.legal.phone ? '' : 'error'"></mt-field>
        <mt-field label="数量" placeholder="每人最多3个" v-model="form.amount" type="number"></mt-field>
      </form>
    </div>
    <div class="bottom">
      <mt-button type="primary" @click="handleSubmit">提交预约</mt-button>
      <mt-button @click="handleReturn">返回首页</mt-button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'appointment',
  components: {
  },
  data() {
    return {
      form: {
        name: '',
        idNumber: '',
        phone: '',
        amount: '',
        legal: {
          idNumber: true,
          phone: true
        }
      },
      config: {
        id: 1,
        name: '',
        begin: '',
        end: '',
        // totalAmount: 10000
        over: false
      }
    }
  },
  methods: {
    async checkPhone() {
      const phoneRegexp = /^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/
      this.form.legal.phone = phoneRegexp.test(this.form.phone)
    },
    async checkIdNumber() {
      const idNumberRegexp = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/
      this.form.legal.idNumber = idNumberRegexp.test(this.form.idNumber)
    },
    async handleSubmit() {
      this.$indicator.open('提交中...')
      const data = {
        name: this.form.name,
        idNumber: this.form.idNumber,
        phone: this.form.phone,
        amount: this.form.amount
      }
      try {
        const res = await this.$api.makeAppointment(data)
        this.$messagebox({
          title: '预约成功',
          message: '您的序列号为 ' + res.serialNumber + ' 请妥善保存',
          confirmButtonText: '我已截图',
          confirmButtonHighlight: true,
          showCancelButton: true,
          cancelButtonText: '复制到剪切板',
          closeOnClickModal: false
        }).then(action => {
          if (action === 'cancel') {
            this.copyToClipboard(res.serialNumber)
            this.$toast({
              message: '已拷贝到剪切板，请及时保存',
              position: 'bottom',
              duration: 2000
            })
            this.$router.replace('/')
          } else if (action === 'confirm') {
            this.$router.replace('/')
          }
        })
      } catch {
        this.$toast({
          message: '预约失败，网络错误',
          position: 'bottom',
          duration: 5000
        });
      } finally {
        setTimeout(() => {
          this.$indicator.close()
        }, 0);
      }
    },
    handleReturn() {
      this.$router.push('/')
    },
    copyToClipboard(text) {
      let $el = document.createElement('input')
      // $el.style.visibility = 'hidden' 此处不能display:none或这样
      $el.style.opacity = 0
      $el.value = text
      $el.readOnly = true
      document.body.append($el)
      $el.select()
      document.execCommand('Copy')
      console.log('已复制 '+ text)
      document.body.removeChild($el)
      $el = null
    }
  },
  async created() {
    const res = await this.$api.checkCanAppointment()
    this.config = res.round
  }
}
</script>

<style scoped lang="scss">
.info-wrapper {
  margin-bottom: 10px;
  background: #f2f2f2;
  text-align: center;
  padding: 10px;
  p {
    margin: 5px 0;
  }
}

.bottom {
  margin-top: 50px;
  margin-bottom: 20px;
  text-align: center;
  button {
    width: 250px;
    margin-bottom: 15px;
    letter-spacing: 1px;
  }
}
</style>