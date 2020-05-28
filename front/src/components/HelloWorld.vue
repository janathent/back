<template>
<div>
  <div id="header">
    <el-menu  class="el-menu-demo" mode="horizontal"  background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" router>
      <el-menu-item>用户：<span>{{$store.state.username}}</span></el-menu-item>
      <el-menu-item index="/myhome" v-if="$store.state.username != '请登录'">个人中心</el-menu-item>
      <el-menu-item index="/showmain">首页</el-menu-item>
      <el-submenu index="4">
        <template slot="title">登录注册</template>
        <el-menu-item index="/login">登录</el-menu-item>
        <el-menu-item index="/register">注册</el-menu-item>
      </el-submenu>
      <el-menu-item index="/manage" >管理员</el-menu-item>
      <el-menu-item index="6"></el-menu-item>

      <strong style="float:left;margin-left:10%;margin-top:10px;font-size: 26px;color: #fff">{{$store.state.bigtitle}}</strong>
      <div style="float: right;margin-top: 20px;margin-right: 35px" v-if="$store.state.username != '请登录'"> <span style="font-size: 12px;color: #fff">{{$store.state.username}}</span></div>

      <el-dropdown style="float: right;margin-right: 8px" v-if="$store.state.username != '请登录'">
        <span class="el-dropdown-link">
           <div style="float: right;margin-right: 8px;margin-top: 10px">
              <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </div>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><span @click="gotomyhome">个人中心</span></el-dropdown-item>
          <el-dropdown-item><span @click="logout">退出登录</span></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>




    </el-menu>
 </div>
  <div>
    <router-view/>
  </div>
</div>
</template>

<script>
export default {
  name: 'HelloWorld',
   data () {
     return {
       msg: 'Welcome to Your Vue.js App'
     }
   },
  methods:{
    logout:function () {
      var _this = this;
      _this.$store.commit('username',"请登录");
      _this.$router.push('/showmain')
    },
    gotomyhome:function () {
      var _this = this;
      if(_this.$store.state.username === '请登录'){
        alert("请先登录");
        _this.$router.push('/login')
      }else{
        _this.$router.push('/myhome')
      }
    }

  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>
/**{*/
/*  margin: 0;*/
/*  padding: 0;*/
/*}*/
</style>
