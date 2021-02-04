<template>
  <div class="blog-edit">
    <div>
      <el-row>
        <el-col :span="2">
          <el-button style="width: 100%" @click="toList">返回文章列表</el-button>
        </el-col>
        <el-col :span="18">
          <el-input style="display: inline" v-model="text.title" placeholder="请输入标题" maxlength="100" show-word-limit></el-input>
        </el-col>
      </el-row>
    </div>
    <mavon-editor @save="save" @imgAdd="imgAdd" style="height: 100%" :value="text.text"></mavon-editor>
  </div>
</template>

<script>
import axios from 'axios'
import ToolHead from "../../components/ui/ToolHead";
//博客编辑页面
export default {
  name: "BlogEdit",
  components: {ToolHead},
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
    if (this.$route.query.textId) {
      this.findById(this.$route.query.textId)
    }
  },
  methods: {
    //保存
    save(value, render) {
      this.text.text = value
      axios.post(process.env.VUE_APP_BASE_API+'text/save', this.text, {
        headers: {
          'Content-Type': 'application/json; charset=UTF-8'
        },withCredentials:true
      }).then(e => {
        this.text = e.data.data
        this.$notify({title: '成功', message: '保存文章成功', type: 'success'});
        // this.text.text = value
      })
    },
    //图片上传
    imgAdd() {
      console.log('图片上传')
    },
    findById(textId) {
      this.$request.get('/text/findById/' + textId, {}, e => {
        this.text = e.data.data
      })
    },
    toList(){
      this.$router.push({path: `/blogList`})//跳转到博客列表
    }
  }
}
</script>

<style scoped>
.blog-edit {
  height: calc(100% - 100px);
}
</style>
