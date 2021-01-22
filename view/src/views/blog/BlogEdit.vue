<template>
  <div class="blog-edit">
    <el-input v-model="text.title" placeholder="请输入标题"></el-input>
    <mavon-editor @save="save" @imgAdd="imgAdd" style="height: 100%" :value="text.text"></mavon-editor>
  </div>
</template>

<script>
  import axios from 'axios'
  //博客编辑页面
  export default {
    name: "BlogEdit",
    components: {},
    data() {
      return {
        text: {
          textId: '',
          userId: '',
          title:'',
          text: '',
          createDate: '',
          updateDate: ''

        }
      }
    },
    created() {
      if (this.$route.query.textId){
        this.findById(this.$route.query.textId)
      }
    },
    methods: {
      //保存
      save(value, render) {
        this.text.text = value
        axios.post('/api/text/save', this.text, {
          headers: {
            'Content-Type': 'application/json; charset=UTF-8'
          }
        }).then(e => {
          this.text = e.data
          // this.text.text = value
        })
      },
      //图片上传
      imgAdd() {
        console.log('图片上传')
      },
      findById(textId){
        this.$request.get('/text/findById/'+textId, {}, e => {
          this.text = e.data
        })
      },
    }
  }
</script>

<style scoped>
  .blog-edit {
    height: calc(100% - 100px);
  }
</style>
