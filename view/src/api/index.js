import axios from "axios";
import router from "../router";

let http = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  withCredentials: true,//表示跨域请求时是否需要使用凭证
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
  },
  transformRequest: [function (data) {
    let newData = '';
    for (let k in data) {
      if (data.hasOwnProperty(k) === true) {
        newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&';
      }
    }
    return newData;
  }]
});
/*请求拦截器 ==>对请求的参数进行处理*/
// http.interceptors.request.use()
/*响应拦截器 ==>对响应的参数进行处理*/
http.interceptors.response.use(response => {
  //如果用户登录失败，重新跳转到登录页面
  if (!response.data.success) {
    alert(response.data.errorMsg)
    router.push({path: `/login`})
  }
  return response

}, error => {
  //响应错误处理
  console.log("error")
  console.log(error)
  return Promise.reject(error)
})

function apiAxios(method, url, params, response) {
  http({
    method: method,
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
  }).then(function (res) {
    response(res);
  }).catch(function (err) {
    response(err);
  })
}

export default {
  get: function (url, params, response) {
    return apiAxios('GET', url, params, response)
  },
  post: function (url, params, response) {
    return apiAxios('POST', url, params, response)
  },
  put: function (url, params, response) {
    return apiAxios('PUT', url, params, response)
  },
  delete: function (url, params, response) {
    return apiAxios('DELETE', url, params, response)
  }
}
