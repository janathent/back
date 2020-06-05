<template>
    <div>
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
            <th>图片数量</th>
            <th>操作</th><th>ID</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(item,index) in list" :key="index">
            <td>{{item.username}}</td>
            <td>{{item.loaction}}</td>
            <td>{{item.shi}}室{{item.ting}}厅{{item.wei}}卫</td>
            <td>{{item.direction}}</td>
            <td>{{item.layernumber}}</td>
            <td>{{item.decoration}}</td>
            <td>{{item.heat}}</td>
            <td>{{item.elevator}}</td>
            <td>{{item.housetype}}</td>
            <td>{{item.propertyrightstype}}</td>
            <td>{{item.propertyrightsyear}}</td>
            <td>{{item.square}}</td>
            <td>{{item.price}}</td>
            <td>{{item.picnumber}}</td>
            <td> <el-button size="mini" round type="success" @click="modifyhouse(item.id)">查看</el-button> <el-button size="mini" round type="danger" @click="deletefun(item.id)">删除</el-button></td>
            <td>{{item.id}}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
</template>

<script>
    export default {
        name: "manageshowsale",
      data(){
          return{
            list:'',
          }
      },
      created() {
          this.init()
      },
      methods:{
          init:function(){
            var _this = this;
            _this.axios.post('/saleshow').then(function (response) {
              _this.list = response.data
            }).catch(function (error) {
              console.log(error)
            })
          },

        deletefun:function(id){
          var _this = this;
          _this.$confirm('此操作将永久删除房屋, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            var formData = new FormData();
            formData.append("id",id);
            let config ={
              headers:{"Content-Type":"multipart/form-data"}
            };
            _this.axios.post("saledelete",formData,config).then(function (response) {
              _this.init()
            }).catch(function (error) {
              console.log(error);
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

        },
        modifyhouse:function (id) {
          // this.$router.push({name:'test',params:{id:id}});
          var _this = this;
          this.$store.commit('changetitle',"查看房屋信息");
          _this.$router.push({path:'/manageshowsaledetail',query:{id:id}});
        },

      }
    }
</script>

<style scoped>

</style>
