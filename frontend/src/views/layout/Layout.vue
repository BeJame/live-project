<template>
  <div class="default-layout">
    <mt-header :fixed="true" :title="title">
      <!-- <mt-button icon="more" slot="right"></mt-button> -->
    </mt-header>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'layout',
  computed: {
    title() {
      return this.$route.meta.title
    }
  },
}
</script>

<style scoped lang="scss">
.default-layout {
  width: 100%;
  padding-top: 40px;
}

</style>

<style lang="scss">
h1.mint-header-title {
  padding: 0;
  margin: 0;
}
</style>