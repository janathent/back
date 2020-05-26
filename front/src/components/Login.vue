<template>
  <div class="container">
    <h1>欢迎登录</h1>
    <div id="login">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名" >
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit" round >登录</el-button>
          <el-button v-on:click="clearall" round>清空</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          username: '',
          password: ''
        }
      }
    },
    methods: {
      onSubmit() {
       var _this = this;
         this.axios.post("/login",{username:this.form.username,password:this.form.password}).then(function (response) {
           console.log(response.data);
           if(response.data.status === 500){
             alert("账户或密码错误！");
             return;
           }
           _this.$store.commit('username',_this.form.username);
           _this.$router.push('/showmain');
         }).catch(function (error) {
           console.log(error)
         })
      },
      clearall(){
        this.form.username = '';
        this.form.password = '';
      },
    }

  }
</script>

<style scoped>
  #login {
    width: 400px;
    height: 100px;
    margin: 0 auto;
  }
</style>
