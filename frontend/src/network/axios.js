import { MessageBox, Toast } from 'mint-ui'

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
    if (res.data.code === 200) {
      return res.data.data
    } else {
      console.warn(res.data.code + '错误\n说明：' + res.data.msg)
      MessageBox({
        message: res.data.code + '：' + res.data.msg,
        title: '错误'
      })
      throw new Error(res.data.code)
    }
  },
  async error => {
    console.warn(Object.entries(error))
    if (error.response.status.toString()[0] === '4') {
      MessageBox({
        message: error.response.status + '：' + error.response.data.msg,
        title: '错误'
      })
    } else {
      MessageBox({
        message: '请求到 ' + error.config.url + ' 异常，状态码：' + error.response.status,
        title: '服务器错误'
      })
    }
    // alert(error.response.status + '错误\n信息：' + error.response.data.data.msg)
    return Promise.reject(error)
  }
)

export default instance