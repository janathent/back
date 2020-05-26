<template>
<div>
  <div id="header">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>用户：<span>{{$store.state.username}}</span></el-breadcrumb-item>
      <el-breadcrumb-item  v-if="$store.state.username != '请登录'" :to="{path: '/myhome'}">个人中心</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/showmain' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/login'}">登录</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/register'}">注册</el-breadcrumb-item>
      <el-breadcrumb-item :to="{path: '/test'}">测试</el-breadcrumb-item>
    </el-breadcrumb>
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
