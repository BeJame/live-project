<template>
  <div class="page-query">
    <p>请输入您的序列号：</p>
    <!-- <mt-field v-model="serialNumber" placeholder="请输入序列号"></mt-field> -->
    <input type="text" v-model="serialNumber">
    <!-- <hr> -->
    <div class="bottom">
      <mt-button type="primary" @click="handleSearch">查询</mt-button>
      <mt-button @click="handleReturn">返回主页</mt-button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'query',
  data() {
    return {
      serialNumber: ''
    }
  },
  methods: {
    async handleSearch() {
      this.$indicator.open('查询中...')
      try {
        const res = await this.$api.getAppointmentResult(this.serialNumber)
        if (!res.isWinner) {
          this.$messagebox.alert('很遗憾，您本轮没有中签', '预约结果')
          return
        }
        // setTimeout(() => {
          this.$indicator.close()
        // }, 0);
        this.$router.push({
          name: 'result',
          params: res.info
        })
      } catch {
        this.$toast({
          message: '网络错误，查询失败',
          position: 'bottom',
          duration: 3000
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
}
</script>

<style scoped lang="scss">
.page-query {
  padding-top: 50px;
}

input {
  line-height: 30px;
  width: 98%;
  margin: -10px 1% 0;
  font-size: 14px;
  border: none;
  border-bottom: 1px solid #ccc;
  outline: none;
}

.bottom {
  margin-top: 150px;
  margin-bottom: 20px;
  text-align: center;
  button {
    width: 250px;
    margin-bottom: 20px;
    letter-spacing: 1px;
  }
}
</style>