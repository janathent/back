<template>
    <div class="container">
      <h1>欢迎注册</h1>
      <div id="register">
        <el-form ref="form" :model="registerForm" label-width="80px">
          <el-form-item label="用户名" >
            <el-input style="width: 220px" v-model="registerForm.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input type="password" style="width: 220px;" v-model="registerForm.password" placeholder="请输入密码" ></el-input>
          </el-form-item>
          <el-form-item label="再次输入">
            <el-input type="password" style="width: 220px;" v-model="registerForm.password1" placeholder="请再次输入密码" ></el-input>
          </el-form-item>
          <el-form-item label="手机">
            <el-input style="width: 220px" v-model="registerForm.phone" placeholder="请输入手机号" ></el-input>
          </el-form-item>

          <el-form-item label="性别">
            <el-select v-model="registerForm.gender" placeholder="请选择">
              <el-option v-for="item in registerForm.options" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="生日">
            <div class="block">
              <el-date-picker v-model="registerForm.birth" align="right" type="date" placeholder="选择日期" :picker-options="registerForm.pickerOptions"></el-date-picker>
            </div>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="onSubmit" @keyup.enter="onSubmit" round>注册</el-button>
            <el-button v-on:click="clearall" round>清空</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data(){
          return{
            registerForm: {
              username: '',
              password: '',
              password1:'',
              phone: '',
              options: [{
                value: '男',
                label: '男'
              }, {
                value: '女',
                label: '女'
              }],
              gender: '',
              pickerOptions: {
                disabledDate(time) {
                  return time.getTime() > Date.now();
                },
                shortcuts: [{
                  text: '今天',
                  onClick(picker) {
                    picker.$emit('pick', new Date());
                  }
                }, {
                  text: '昨天',
                  onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24);
                    picker.$emit('pick', date);
                  }
                }, {
                  text: '一周前',
                  onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', date);
                  }
                }]
              },
              birth: '',
            }
          }
        },

      methods: {
        onSubmit(){
          var _this = this;
          if(this.registerForm.username === '' || this.registerForm.password === '' || this.registerForm.phone === '' || this.registerForm.gender === '' || this.registerForm.birth === ''){
            alert("请填写完整");
            return;
          }
          if(_this.registerForm.password !== _this.registerForm.password1){
            alert("两次密码输入不一致，请重新输入！");
            return;
          }
    //      this.axios.post("/test",{p1:v1,p2:v2}).then(function (response) {console.log(response)}).catch(function (error) {console.log(error)})
          this.axios.post("/register",{
            username:this.registerForm.username,
            password:this.registerForm.password,
            phone:this.registerForm.phone,
            gender:this.registerForm.gender,
            birth:this.registerForm.birth
          }).then(function (response) {
            console.log(response.data);
            if(response.data === "成功"){
              alert("注册成功！确定后跳转登录页面");
              _this.$router.push('/login');
            }else if(response.data === "用户名已经存在"){
              alert("注册失败！用户名已经存在");
            }
          }).catch(function (error) {
            console.log(error);
          });
         // this.axios.post("/register",{username:this.registerForm.username, password:this.registerForm.password}).then(function (response) {console.log(response.data);if(response.data === "成功"){alert("注册成功！")}}).catch(function (error) {console.log(error)})
        },
        clearall: function (){
          this.registerForm.username = '';
          this.registerForm.password = '';
          this.registerForm.phone = '';
          this.registerForm.gender = '';
          this.registerForm.birth = '';
      }

      }
    }
</script>

<style scoped>
  /*.container{*/
  /*  height: 100%;*/
  /*  width: 100%;*/
  /*  background-image: url("../../static/bg.png");*/
  /*  background-size: cover;*/
  /*  position: fixed;*/
  /*  left: 0px;*/
  /*  top:0px;*/
  /*}*/
  /*.form-body{*/
  /*  border-radius: 10px;*/
  /*  margin: 100px auto auto;*/
  /*  width: 25%;*/
  /*  min-width: 200px;*/
  /*  padding: 30px 30px 15px 30px;*/
  /*  background-color: rgba(255,255,255,0.5);*/
  /*  box-shadow: 5px 3px 10px rgba(0,0,0,0.9);*/
  /*}*/
  /*.form-confirm{*/
  /*  width: 100%;*/
  /*  background-color: #3388B1;*/
  /*}*/

  #register {
    width: 400px;
    height: 100px;
    margin: 0 auto;
  }
</style>
