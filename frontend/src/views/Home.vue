<template>
  <div class="wrapper">
    <h1 align="center">福大口罩预约系统</h1>
    <div class="button">
      <div class="box"><img src="../asserts/images/mask.png" slot="icon"><span class="text">我要预约</span></div>
    </div>
    <div class="button">
      <div class="box"><img src="../asserts/images/mask.png" slot="icon"><span class="text">我要查询</span></div>
    </div>
    <div class="change">
      <mt-button type="primary" size="normal">新建预约</mt-button>
      <mt-button type="primary" size="normal">结束当前</mt-button>
    </div>
  </div>
</template>

<script>

export default {
  name: '',
  components: {
  }
}
</script>

<style>
  .wrapper{
    background: url('../asserts/images/bg.png') no-repeat;
    background-size: cover;
    height: 100%;
    background-position: center center;
  }
  .button{
    width:70%;
    height: 100px;
    margin: 0 auto;
    background-color: white;
    margin-bottom: 100px;
  }
  img {
    width: 60px; 
    height: 60px;
    position: absolute;
    top: 50%;
    margin-top : -30px;
  }
  h1{
    /* margin: 0 auto; */
    padding-top: 100px;
    margin-bottom: 100px;
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
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  }
  .change{
    display: flex;
    justify-content: space-around;
  }
</style>