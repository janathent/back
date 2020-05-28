<template>
  <div>
    <el-menu  class="el-menu-demo" mode="horizontal"  background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" router>
      <el-menu-item>用户：<span>{{$store.state.username}}</span></el-menu-item>
      <el-menu-item index="2" v-if="$store.state.username != '请登录'" :to="{path: '/myhome'}">个人中心</el-menu-item>
      <el-menu-item index="/showmain">首页</el-menu-item>
      <el-submenu index="4">
        <template slot="title">登录注册</template>
        <el-menu-item index="/login">登录</el-menu-item>
        <el-menu-item index="/register">注册</el-menu-item>
      </el-submenu>
      <el-menu-item index="5" >消息中心</el-menu-item>
      <el-menu-item index="6"></el-menu-item>
    </el-menu>

  </div>
</template>

<script>
  export default {
    data() {
      return{
        count: 0
      }
    },
    created() {

    }
    ,
    methods:{
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },

    }
  }
</script>
<style scoped>

</style>
