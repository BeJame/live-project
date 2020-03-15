import Home from '../views/Home.vue'
import Layout from '../views/layout/Layout.vue'
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'home',
    component: Home,
  },
  {
    path: '/',
    redirect: '/home',
    component: Layout,
    children: [
      {
        path: '/appointment',
        name: 'appointment',
        component: () => import('../views/appointment.vue'),
        meta: { title: '口罩预约' }
      },
      {
        path: '/query',
        name: 'query',
        component: () => import('../views/query.vue'),
        meta: { title: '中签查询' }
      },
      {
        path: '/result',
        name: 'result',
        component: () => import('../views/result.vue'),
        meta: { title: '中签结果' }
      },
    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
