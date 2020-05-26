<template>
  <div>
    <table class="el-table">
      <thead>
      <tr>
        <th>用户名</th>
        <th>房屋上传</th>
        <th>上传管理</th>
        <th>收藏纪录</th>
        <th>浏览纪录</th>
        <th>修改个人信息</th>
        <th>消息中心</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,index) in list" :key="index">
        <td>{{item.username}}</td>
        <td><el-switch v-model="item.houseupload" active-text="开" inactive-text="关" active-value="1" inactive-value="0" @change="modifyauthority(item)"></el-switch></td>
        <td><el-switch v-model="item.uploadmanage" active-text="开" inactive-text="关" active-value="1" inactive-value="0" @change="modifyauthority(item)"></el-switch></td>
        <td><el-switch v-model="item.collectionrecord" active-text="开" inactive-text="关" active-value="1" inactive-value="0" @change="modifyauthority(item)"></el-switch></td>
        <td><el-switch v-model="item.browserrecord" active-text="开" inactive-text="关" active-value="1" inactive-value="0" @change="modifyauthority(item)"></el-switch></td>
        <td><el-switch v-model="item.infomodify" active-text="开" inactive-text="关" active-value="1" inactive-value="0" @change="modifyauthority(item)"></el-switch></td>
        <td><el-switch v-model="item.mymessage" active-text="开" inactive-text="关" active-value="1" inactive-value="0" @change="modifyauthority(item)"></el-switch></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
    export default {
        name: "ManageUserAuthority",
      data(){
          return{
            list:'',
          }
      },
      created() {
          var _this = this;
          _this.axios.post('/findallauthority').then(function (response) {
            _this.list = response.data;
          }).catch(function (error) {
            console.log(error);
          })
      },
      methods:{
        modifyauthority:function (item) {
          var _this = this;
          _this.axios.post('/updateauthoritybyusername',{username:item.username,houseupload:item.houseupload,uploadmanage:item.uploadmanage
          ,collectionrecord:item.collectionrecord,browserrecord:item.browserrecord,infomodify:item.infomodify,mymessage:item.mymessage}).then(function (response) {
          }).catch(function (error) {
            console.log(error)
          })

          }
      }
    }
</script>

<style scoped>

</style>
