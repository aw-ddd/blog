<template>
  <div class="too-head">
    <el-menu :default-active="activeIndex"
             class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">博客列表</el-menu-item>
      <el-menu-item index="2" style="float: right">
        <el-button icon="el-icon-edit" type="danger" plain>写博客</el-button>
      </el-menu-item>
      <el-popover
          placement="bottom"
          width="200"
          trigger="hover">
        <div class="info" @click="logout">退出</div>
        <el-image slot="reference" :src="'http://localhost:8888/images/鹿.png'" class="header-image"></el-image>
      </el-popover>
    </el-menu>

  </div>
</template>

<script>
export default {
  name: "ToolHead",
  data() {
    return {
      activeIndex: '1',
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      //获取当前路由
      switch (key) {
        case '1': {
          if (this.$route.path !== '/blogList') {
            this.$router.push({path: `/blogList`})//跳转到博客列表
          }
          break
        }
        case '2' :
          this.$router.push({path: `/blogEdit`}) //跳转到编辑博客页面
          break
      }
    },
    //退出
    logout(){
      this.$request.get("/user/logout",{},e=>{
        if (e.data.success){
          this.$notify.success({title: "退出登录", message: "成功"})
          this.$router.push({path:`login`})
        }else{
          this.$notify.error({title: "退出登录", message: "失败"})
        }
      })
    }
  },

}
</script>

<style scoped>
.too-head{
  margin-bottom: 10px;
}
.header-image{
  height: 50px;
  width: 50px;
  border-radius: 50px;
  margin-left: calc(100% - 400px);
  margin-top: 5px;
}
.info{
  width: 100%;
  height: 30px;
  text-align: left;
  line-height: 30px;
  color: #909399;
}
</style>
