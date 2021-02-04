<template>
  <div>
    <el-card class="login-card">
      <div slot="header" >
        <span>用户登录</span>
      </div>
      <el-form :model="user"  label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.password" show-password></el-input>
        </el-form-item>
        <el-button type="primary" size="medium" @click="login" class="login-button">登录</el-button>
        <el-button type="warning" size="medium" @click="restUser" >重置</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";
import qs from "qs"
export default {
  //登录页面
  name: "Login",
  data(){
    return{
      user:{
        username:"",
        password:""
      }
    }
  },
  methods:{
    //登录
    login(){
      axios.post(process.env.VUE_APP_BASE_API+"user/login",qs.stringify(this.user),{
        headers:{
          'Content-Type':' application/x-www-form-urlencoded'
        },withCredentials: true,//表示跨域请求时是否需要使用凭证
      }).then(e=>{
        if (e.data.success && e.data.errorCode === 200){
          this.$request.get("/user/findUser/"+this.user.username,{},e=>{
            console.log(e);
            console.log(this.$store);
          })
          //登录成功
          this.$router.push({path: `/blogList`})//跳转到博客列表
          this.$notify.success({title:"登录成功",message:e.data.errorMsg})

        }else {
          this.$notify.error({title:"登录失败",message:e.data.errorMsg})
        }
      })
    },
    //重置登录信息
    restUser(){
      this.user = {
        username : '',
        password: ''
      }
    }
  }
}
</script>

<style scoped>
.login-card{
  width: 400px;
  margin: 100px auto;
}
.login-button{
  margin-left: calc(100% - 150px);
}
</style>
