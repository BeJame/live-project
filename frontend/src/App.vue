<template>
  <div id="app">
    <router-view></router-view>
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
  height: 100%;
  /* height: 100vh; */
}

.el-container, header.el-header, .el-main {
  margin: 0;
  padding: 0;
}

#app {
  height: 100%;
}
</style>
