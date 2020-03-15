import './plugins/Mint'

import App from './App.vue'
import Vue from 'vue'
import api from './network/index'
import router from './router'
import store from './store'

Vue.config.productionTip = false

Vue.prototype.$api = api

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
