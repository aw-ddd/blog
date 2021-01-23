<template>
  <div>
    <el-menu :default-active="activeIndex"
             class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">博客列表</el-menu-item>
      <el-menu-item index="2" style="float: right">
        <el-button icon="el-icon-edit" type="danger" plain>写博客</el-button>
      </el-menu-item>
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
      switch (key) {
        case '1':
          this.$router.push({path: `/blogList`}) //跳转到博客列表
        case '2' :
          this.$router.push({path: `/blogEdit`}) //跳转到编辑博客页面
      }
    }
  }
}
</script>

<style scoped>

</style>
