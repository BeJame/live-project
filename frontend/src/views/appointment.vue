<template>
  <div class="page-appointment">
    <div class="info-wrapper">
      <p class="info-line">本轮预约是第 {{ config.id }} 轮</p>
      <p class="info-line">本次预约时间：{{ config.begin }} - {{ config.end || '未设定' }}</p>
      <p class="info-line">本次预约口罩数量：{{ config.totalAmount || '未设定' }}</p>
    </div>
    <div class="form-wrapper">
      <form>
        <mt-field label="姓名" v-model="form.name"></mt-field>
        <mt-field @blur="checkIdNumber" label="身份证" v-model="form.idNumber" :state="form.legal.idNumber ? '' : 'error'"></mt-field>
        <mt-field @blur="checkPhone" label="手机号" v-model="form.phone" type="tel" :state="form.legal.phone ? '' : 'error'"></mt-field>
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
      const res = await this.$api.checkPhone(this.form.phone)
      if (!res) {
        this.form.legal.phone = false
      }
    },
    async checkIdNumber() {
      const res = await this.$api.checkPhone(this.form.idNumber)
      if (!res) {
        this.form.legal.phone = false
      }
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
          message: '您的序列号为：' + res.serialNumber + '，请妥善保存',
          confirmButtonText: '我已保存',
          confirmButtonHighlight: true,
          showCancelButton: true,
          cancelButtonText: '复制序列号',
          closeOnClickModal: false
        }).then(action => {
          if (behavior === 'cancel') {
            // 复制,then
            this.$toast({
              message: '已拷贝到剪切板，请及时保存',
              position: 'bottom',
              duration: 2000
            })
          } else if (behavior === 'confirm') {
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