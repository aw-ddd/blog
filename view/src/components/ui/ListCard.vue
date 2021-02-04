<template>
  <div>
    <el-card class="box-card">
      <el-link type="primary" icon="el-icon-s-order" @click="toShow(text.textId)"><span
          style="font-size: 18px">{{ text.title }}</span></el-link>
      <div class="update-time">
        <i class="el-icon-pie-chart"></i>
        {{ text.updateDate ? $dateUtils.formatStandardDate(text.updateDate) : $dateUtils.formatStandardDate(text.createDate) }}
      </div>
      <div class="edit-view">
        <el-link @click="toEdit(text.textId)">编辑</el-link>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "ListCard",
  props: {
    text: Object
  },
  created() {
  },
  methods: {
    toShow(textId) {
      this.$router.push({path: `/blogShow`, query: {textId}})
    },
    toEdit(textId){
      this.$router.push({path:`/blogEdit`,query:{textId}})
    }
  }
}
</script>

<style scoped>
.box-card {
  width: 70%;
  margin: 0 auto;
  box-sizing: border-box;
}

.update-time {
  margin: 15px 0 -10px 0;
  font-size: 5px;
  color: #d7b5aa;
}
.edit-view{
  float: right;
  padding-bottom: 8px;
}
</style>
