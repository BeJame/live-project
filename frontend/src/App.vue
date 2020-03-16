<template>
  <div id="app">
    <transition :name="transitionName">
      <!-- <keep-alive> -->
        <router-view></router-view>
      <!-- </keep-alive> -->
    </transition>
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
  data() {
    return {
      transitionName: 'slide-left'
    }
  },
  watch: {
    $route(to, from) {
      //如果to索引大于from索引,判断为前进状态,反之则为后退状态
      if (to.meta.index > from.meta.index) {
        this.transitionName = 'slide-left';
      } else {
        this.transitionName = 'slide-right';
      }
    }
  },
}
</script>

<style>
html, body {
  font-family: 'Microsoft YaHei';
  width: calc(100% - 8px);
  /* min-height: 100%; */
}

#copyright {
  position: absolute;
  bottom: -10px;
  width: calc(100% - 16px);
  font-size: 12px;
  text-align: center;
  opacity: .5;
}

.slide-right-enter-active,
.slide-right-leave-active,
.slide-left-enter-active,
.slide-left-leave-active {
  will-change: transform;
  transition: all 500ms;
  position: absolute;
}
.slide-right-enter {
  opacity: 0;
  transform: translate3d(-100%, 0, 0);
}
.slide-right-leave-active {
  opacity: 0;
  transform: translate3d(100%, 0, 0);
}
.slide-left-enter {
  opacity: 0;
  transform: translate3d(100%, 0, 0);
}
.slide-left-leave-active {
  opacity: 0;
  transform: translate3d(-100%, 0, 0);
}
</style>
