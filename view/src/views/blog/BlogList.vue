<template>
  <div>
    <el-container>
      <el-header>
        <el-button @click="toEdit">写博客</el-button>
      </el-header>
      <el-main>
        <div v-for="e in textList" :key="e.textId">
          <el-link type="primary" icon="el-icon-s-order"
                   @click="toShow(e.textId)">{{ e.title }}
          </el-link>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "BlogList",
  data() {
    return {
      textList: []
    }
  },
  created() {
    this.findAll()
  },
  methods: {
    findAll() {
      this.$request.get('/text/findAll', {}, e => {
        this.textList.push(...e.data)
      })
    },
    //跳转编辑页面
    toEdit() {
      this.$router.push({path: `/blogEdit`})
    },
    //跳转到详情页面
    toShow(textId) {
      this.$router.push({path: `/blogShow`,query:{textId}})
    }
  }
}
</script>

<style scoped>

</style>
