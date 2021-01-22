<template>
  <div class="home">
    <el-button @click="submit">按钮</el-button>
    <mavon-editor v-model="content" @save="save" @imgAdd="imgAdd"></mavon-editor>
  </div>
</template>

<script>

  export default {
    name: 'Home',
    components: {},
    data() {
      return {
        content: ''
      }
    },
    methods: {
      submit() {
        this.$request.get('/user/findAll', {}, (res) => {
          console.log(res);
        })
      },
      save() {
        console.log('md文件正在被保存');
      },
      imgAdd() {
        console.log('图片正在上传')
      }
    }
  }
</script>
