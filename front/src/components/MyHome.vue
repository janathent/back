<template>
<!--  个人主页-->
<div>
  <el-container style="height:700px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '2','3','4']" router>
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-s-goods"></i>我想卖的</template>
          <el-menu-item-group>
            <el-menu-item index="/saleupload" v-on:click="changetitle1" :disabled="houseupload">房屋上传</el-menu-item>
            <el-menu-item index="/salemanage" v-on:click="changetitle2" :disabled="uploadmanage">上传管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="2">
          <template slot="title"><i class="el-icon-shopping-cart-2"></i>我想买的</template>
          <el-menu-item-group>
            <el-menu-item index="/mycollection" v-on:click="changetitle5" :disabled="collectionrecord">收藏纪录</el-menu-item>
            <el-menu-item index="/mybrowse" v-on:click="changetitle6" :disabled="browserrecord">浏览纪录</el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="3">
          <template slot="title"><i class="el-icon-user-solid"></i>个人信息</template>
          <el-menu-item-group>
            <el-menu-item index="/personalinfomodify" v-on:click="changetitle3" :disabled="infomodify">修改信息</el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="4">
          <template slot="title"><i class="el-icon-message"></i>消息中心</template>
          <el-menu-item-group>
            <el-menu-item index="/mymessage" v-on:click="changetitle4" :disabled="mymessage">我的消息</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</div>
</template>

<script>
    export default {
        name: "MyHome",
        data() {
          return {
            title:'',
            houseupload:'',
            uploadmanage:'',
            collectionrecord:'',
            browserrecord:'',
            infomodify:'',
            mymessage:''
          }
        },
      created() {
          //处理权限问题
        var _this = this;
        if(_this.$store.state.username === "请登录"){
          alert("请登录");
        }else{
          var formData = new FormData();
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          formData.append('username',_this.$store.state.username);
          _this.axios.post("/findauthoritybyusername",formData,config).then(function (response) {
            if(response.data[0].houseupload === "0"){
              _this.houseupload = true;
            }else{
              _this.houseupload = false;
            }

            if(response.data[0].uploadmanage === "0"){
              _this.uploadmanage = true;
            }else{
              _this.uploadmanage = false;
            }

            if(response.data[0].collectionrecord === "0"){
              _this.collectionrecord = true;
            }else{
              _this.collectionrecord = false;
            }

            if(response.data[0].browserrecord === "0"){
              _this.browserrecord = true;
            }else{
              _this.browserrecord = false;
            }

            if(response.data[0].infomodify === "0"){
              _this.infomodify = true;
            }else{
              _this.infomodify = false;
            }

            if(response.data[0].mymessage === "0"){
              _this.mymessage = true;
            }else{
              _this.mymessage = false;
            }
          }).catch(function (error) {
            console.log(error)
          });
        }


      },
      methods:{
            changetitle1:function () {
              this.$store.commit('changetitle',"房屋信息上传");
              // this.title = "房屋信息上传";
            },
          changetitle2:function () {
            this.$store.commit('changetitle',"房屋上传管理");
              // this.title = "房屋上传管理";
          },
          changetitle3:function () {
            this.$store.commit('changetitle',"修改个人信息");
          },
          changetitle4:function () {
            this.$store.commit('changetitle',"聊天室");
          },
          changetitle5:function () {
            this.$store.commit('changetitle',"我的收藏");
          },
          changetitle6:function () {
            this.$store.commit('changetitle',"我的浏览记录");
          }
        }
    }

</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
