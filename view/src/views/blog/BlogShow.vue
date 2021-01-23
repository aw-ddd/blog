<template>
  <div style="height: 100%;width: 80%;margin: 0 auto">
    <!--
    value:初始值
    edittable:是否允许编辑
    toolbarsFlag:是否显示工具栏
    subfield:默认双栏显示
    defaultOpen:默认打开预览页
    -->
    <mavon-editor style="height: 100%"
                  :value="text.text"
                  :editable="false"
                  :toolbarsFlag="false"
                  :subfield="false"
                  defaultOpen="preview">
    </mavon-editor>
  </div>
</template>

<script>

export default {
  name: "BlogShow",
  data() {
    return {
      text: {
        textId: '',
        userId: '',
        title: '',
        text: '',
        createDate: '',
        updateDate: ''
      }
    }
  },
  created() {
    this.findById(this.$route.query.textId)
  },
  methods: {
    findById(textId) {
      this.$request.get('/text/findById/' + textId, {}, e => {
        this.text = e.data
      })
    }
  }
}
</script>

<style scoped>

</style>
