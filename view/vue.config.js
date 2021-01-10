module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8888/',  // 此处为接口地址
        changeOrigin: true,   // 是否允许跨域
        pathRewrite: {  // 重写路径 目的是把/api开始的接口重写 （如/api/users 改成/users）
          '^/api': '/'
        }
      }
    }
  }
}
