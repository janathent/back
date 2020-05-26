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
        <td> <el-button size="mini" round type="success" @click="godetailer(item.id)">查看</el-button>
        <td>{{item.id}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
    export default {
        name: "MyCollection",
        data() {
          return {
            list:''
          };
        },
       created() {
         var _this = this;
         var formData = new FormData();
         formData.append('username',_this.$store.state.username);
         let config = {
           headers:{"Content-Type":"multipart/form-data"}
         };
         _this.axios.post("/returnallcollection",formData,config).then(function (response) {
           console.log(response)
           _this.list = response.data
         }).catch(function (error) {
           console.log(error)
         })
       },
      methods:{
          godetailer:function (id) {
            this.$router.push({path:'/saledetailer',query:{id:id}});
          }
      }
    }
</script>

<style scoped>

</style>
