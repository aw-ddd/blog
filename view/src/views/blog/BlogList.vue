<template>
  <div>
    <tool-head></tool-head>
    <div v-for="e in textList" :key="e.textId" style="margin-bottom: 20px">
      <list-card :text="e"></list-card>
    </div>
  </div>
</template>

<script>
import ToolHead from "../../components/ui/ToolHead";
import ListCard from "../../components/ui/ListCard";

export default {
  name: "BlogList",
  components: {ListCard, ToolHead},
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
      this.$router.push({path: `/blogShow`, query: {textId}})
    }
  }
}
</script>

<style scoped>

</style>
