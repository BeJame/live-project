<template>
  <div class="page-home">
    <h1 align="center">福大口罩预约系统</h1>
    <div class="button" @click="handleClickAppointment">
      <div class="box"><img src="../assets/mask.png" slot="icon"><span class="text">我要预约</span></div>
    </div>
    <div class="button" @click="handleClickQuery">
      <div class="box"><img src="../assets/mask.png" slot="icon"><span class="text">我要查询</span></div>
    </div>
    <div class="change">
      <mt-button type="primary" size="normal" @click="handleClickNew">新建预约</mt-button>
      <mt-button type="primary" size="normal" @click="handleClickStop">结束当前</mt-button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'home',
  methods: {
    async handleClickAppointment() {
      const res = await this.$api.checkCanAppointment()
      if (res.status) {
        this.$router.push(`/appointment?round=${res.round.id}`)
      } else {
        this.$messagebox.alert('目前不是开放预约时间！', '提示')
      }
    },
    handleClickQuery() {
      this.$router.push('/query')
    },
    async handleClickNew() {
      console.log(this.$api)
      await this.$api.TEST_start()
      this.$toast({
        message: '√操作成功',
      })
    },
    async handleClickStop() {
      await this.$api.TEST_end()
      this.$toast({
        message: '√操作成功',
      })
    }
  },
}
</script>

<style scoped>
  .page-home{
    width: 100%;
    background: url('../assets/bg.png') no-repeat;
    background-size: cover;
    height: 100%;
    background-position: center center;
  }
  .button{
    box-sizing: border-box;
    width: 80%;
    height: 100px;
    margin: -30px auto;
    padding: 0 15px;
    background-color: white;
    margin-bottom: 100px;
    border-radius: 4px;
    box-shadow: 0 0 10px 1px #ddd;
  }
  img {
    width: 60px; 
    height: 60px;
    position: absolute;
    top: 50%;
    margin-top : -30px;
  }
  h1{
    margin: 0 auto;
    padding: 80px 0;
    color:white;
  }
  .box {
    position: relative;
    line-height: 100px;
    width: 100%;
  }
  .text {
    position: relative;
    left: 100px;
    font-weight: bold;
    font-size: 18px;
  }
  .change{
    display: flex;
    justify-content: space-evenly;
    margin-top: -30px;
  }
</style>