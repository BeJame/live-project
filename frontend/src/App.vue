<template>
  <div id="app">
    <router-view></router-view>
    <div id="copyright">
      copyright&copy;2020 云玩家-软工实践11组
      <br>
      <b>本程序非真实上线，请悉知</b>
    </div>
  </div>
</template>

<script>
export default {
  name: 'app',
}
</script>

<style>
html, body {
  font-family: 'Microsoft YaHei';
  min-height: 100%;
}

#copyright {
  position: absolute;
  bottom: 0;
  width: 100%;
  font-size: 12px;
  text-align: center;
  opacity: .5;
}

</style>
