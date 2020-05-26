<template>
    <div>
      <table class="el-table">
        <thead>
        <tr>
          <th>用户名</th>
          <th>出生年月</th>
          <th>性别</th>
          <th>手机号</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item,index) in list" :key="index">
          <td>{{item.username}}</td>
          <td>{{item.birth}}</td>
          <td>{{item.gender}}</td>
          <td>{{item.phone}}</td>
          <td>
            <el-button size="mini" round type="success" @click="modifyuser(item.username)">修改</el-button>
            <el-button size="mini" round type="danger" @click="deleteuser(item.id,item.username)">删除</el-button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
</template>

<script>
    export default {
        name: "ManageUser",
      data() {
        return {
          list:'',
        }
      },
      created() {
          var _this = this;
          _this.axios.post('/managefindalluser').then(function (response) {
            _this.list = response.data;
          }).catch(function (error) {
            console.log(error)
          })
      },
      methods:{
        modifyuser:function (username) {
          this.$router.push({path:'/managemodifyuser',query:{username:username}});
        },
        deleteuser:function (id,username) {
          var _this = this;
          var formData = new FormData();
          formData.append("id",id);
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          _this.$confirm('此操作将永久删除用户, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            _this.axios.post('/deleteuserbyid',formData,config).then(function (response) {

            }).catch(function (error) {
              console.log(error)
            })
            var formData1 = new FormData();
            formData1.append('username',username);
            _this.axios.post('/deleteauthoritybyusername',formData1,config).catch(function (response) {

            }).catch(function (error) {
              console.log(error)
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
          _this.axios.post('/managefindalluser').catch(function (response) {
            _this.list = response.data
          }).catch(function (error) {
            console.log(error)
          })
        }
      }
    }
</script>

<style scoped>

</style>
