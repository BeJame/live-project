import { Message, MessageBox } from 'element-ui'

import axios from 'axios'
import router from '../router'
import store from '../store'

const instance = axios.create({
  baseURL: 'http://47.97.115.41:8087/'
})

instance.interceptors.request.use(
  config => {
    return config
  },
  error => {
    console.log(error) // for debug
    return Promise.reject(error)
  })
  
instance.interceptors.response.use(
  res => {
  // if (typeof(res.data) === 'string' && res.data.includes('<!DOCTYPE html>')) {
  //   MessageBox.alert('登录过期或尚未登录，请重新登录。', '提醒').then(_ => {
  //     store.commit('setLoginState', false)
  //     router.push('/login')
  //   })
  //   return
  // } else if (typeof(res.data) === 'string') {
  //   // useless
  //   res.headers['content-type'] = 'application/pdf'
  //   console.log(res.headers)
  //   return res.data // special for download
  // }

  // if (res.data.code === 0) {
  //   return res.data.data
  // } else {
  //   console.warn(res)
  //   Message.error({
  //     message: '服务器错误，错误码：' + res.data.code,
  //     duration: '4000'
  //   })
    if (res.data.code === 200) {
      return res.data.data
    } else {
      alert(res.data.code + '错误')
    }
  // }
  },
  async error => {
    console.warn(Object.entries(error))
    // if (error.response.status.toString()[0] === '4') {
    //   Message.error({
    //     message: `${error.response.data.data.msg}(${error.response.data.code})`,
    //     duration: '3000'
    //   })
    // } else {
    //   Message.error({
    //     message: '请求 ' + error.config.url + ' 异常，状态码：' + error.response.status,
    //     duration: '5000'
    //   })
    // }
    return Promise.reject(error)
  }
)

export default instance