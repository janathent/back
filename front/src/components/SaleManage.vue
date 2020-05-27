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
        <td> <el-button size="mini" round type="success" @click="modifyhouse(item.id)">修改</el-button> <el-button size="mini" round type="danger" @click="deletefun(item.id)">删除</el-button></td>
        <td>{{item.id}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
    export default {
        name: "salemanage",
      data() {
        return {
          list:''
        };
      },
      created() {
        this.init();
      },
      methods: {
        deletefun:function(id){
          alert("确定要删除房屋吗？")
          var _this = this;
          var formData = new FormData();
          formData.append("id",id);
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          this.axios.post("saledelete",formData,config).then(function (response) {
            _this.init();
            alert("删除成功");
          }).catch(function (error) {
            console.log(error);
            alert("删除失败");
          })
        },
        modifyhouse:function (id) {
          // this.$router.push({name:'test',params:{id:id}});
          var _this = this;
          this.$store.commit('changetitle',"修改房屋信息");
          _this.$router.push({path:'/salemodify',query:{id:id}});
        },
        // beforeRemove(file, fileList) {
        //   //return this.$confirm(`确定移除 ${ file.name }？`);
        // },
        // handleRemove(file, fileList) {
        //   console.log(file, fileList);
        // },
        // handlePictureCardPreview(file) {
        //   this.dialogImageUrl = file.url;
        //   this.dialogVisible = true;
        // },
        // fun(file,fileList){
        //   this.fileList = fileList;
        // },
        // submit:function () {
        //   var formData = new FormData();
        //    console.log(this.fileList);
        //   // for(var j = 0 ; j < this.fileList.length ;++j){
        //   //   console.log(this.fileList[j].raw);
        //   // }
        //   for(let item of this.fileList){
        //     formData.append('image',item.raw);
        // }
        //   let config ={
        //     headers:{"Content-Type":"multipart/form-data"}
        //   };
        //   this.axios.post("/test",formData,config).then(function (response) {
        //     console.log(response);
        //   }).catch(function (error) {
        //     console.log(error);
        //   });

        init:function () {
          var _this = this;
          var formData = new FormData();
          formData.append("username",this.$store.state.username);
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          this.axios.post("/salemanage",formData,config).then(function (response) {
            _this.list = response.data;
          }).catch(function (error) {
            console.log(error);
          })

        }
      }

    }
</script>

<style scoped>
  ul li{
    list-style-type: none;
  }

</style>
