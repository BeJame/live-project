import 'mint-ui/lib/style.css'

import MintUI from 'mint-ui'
import Vue from 'vue'

Vue.use(MintUI)

Vue.prototype.$messagebox = MintUI.MessageBox
Vue.prototype.$toast = MintUI.Toast
Vue.prototype.$indicator = MintUI.Indicator