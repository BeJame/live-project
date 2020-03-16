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
    meta: { index: 1 }
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
        meta: { title: '口罩预约', index: 2 }
      },
      {
        path: '/query',
        name: 'query',
        component: () => import('../views/query.vue'),
        meta: { title: '中签查询', index: 2 }
      },
      {
        path: '/result',
        name: 'result',
        component: () => import('../views/result.vue'),
        meta: { title: '中签结果', index: 3 }
      },
    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
