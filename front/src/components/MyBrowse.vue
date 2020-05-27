<template>
  <div class="container">
    <table class="el-table">
      <thead>
      <tr>
        <th>卖家姓名</th>
        <th>地点</th>
        <th>室/厅/卫</th>
        <th>朝向</th>
        <th>层数</th>
        <th>装修情况</th>
        <th>供暖情况</th>
        <th>电梯情况</th>
        <th>房屋类型</th>
        <th>产权类型</th>
        <th>产权年限</th>
        <th>房屋面积</th>
        <th>售价</th>
        <th>时间</th>
        <th><el-link type="danger" @click="clearbrowse">清空记录</el-link></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,index) in list" :key="index">
        <td>{{item.list.username}}</td>
        <td>{{item.list.loaction}}</td>
        <td>{{item.list.shi}}室{{item.list.ting}}厅{{item.list.wei}}卫</td>
        <td>{{item.list.direction}}</td>
        <td>{{item.list.layernumber}}</td>
        <td>{{item.list.decoration}}</td>
        <td>{{item.list.heat}}</td>
        <td>{{item.list.elevator}}</td>
        <td>{{item.list.housetype}}</td>
        <td>{{item.list.propertyrightstype}}</td>
        <td>{{item.list.propertyrightsyear}}</td>
        <td>{{item.list.square}}</td>
        <td>{{item.list.price}}</td>
        <td>{{item.time}}</td>
        <td> <el-button size="mini" round type="success" @click="gotodetailer(item.list.id)">查看</el-button> </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
    export default {
        name: "MyBrowse",
      data(){
          return{
            list:[],

          }
      },
      methods:{
        gotodetailer:function (id) {
          this.$router.push({path:'/saledetailer',query:{id:id}});
        },
        clearbrowse:function () {
          var _this = this;
          _this.$confirm('此操作将永久浏览记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            var formData = new FormData();
            formData.append('username',_this.$store.state.username);
            let config ={
              headers:{"Content-Type":"multipart/form-data"}
            };
            _this.axios.post("/deletebrowsebyusername",formData,config).then(function (response) {
              console.log(response)
            }).catch(function (error) {
              console.log(error)
            })
            _this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        }

      },
      created() {
          var _this = this;
          if(_this.$store.state === "请登录"){
            alert("请登录！");
            return;
          }
          var formData = new FormData();
          formData.append('username',_this.$store.state.username);
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          _this.axios.post('/findallbrowsebyusername',formData,config).then(function (response) {
            for(var i = 0 ; i < response.data.length ;++i){
              var obj = {list:'',time:''};
              for(var temp in response.data[i]){
                obj.list = JSON.parse(temp);
                obj.time = response.data[i][temp];
                _this.list.push(obj);
              }
            }
          }).catch(function (error) {
            console.log(error)
          })
      }
    }
</script>

<style scoped>

</style>
