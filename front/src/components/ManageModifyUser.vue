<template>
  <div>
    <div class="container">
      <h1>修改{{username}}信息</h1>
      <div id="infomodify">
        <el-form ref="form" :model="userinfo" label-width="80px">

          <el-form-item label="手机">
            <el-input style="width: 220px" v-model="userinfo.phone" placeholder="请输入手机号" ></el-input>
          </el-form-item>

          <el-form-item label="性别">
            <el-select v-model="userinfo.gender" placeholder="请选择">
              <el-option v-for="item in userinfo.options" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="生日">
            <div class="block">
              <el-date-picker v-model="userinfo.birth" align="right" type="date" placeholder="选择日期" :picker-options="userinfo.pickerOptions"></el-date-picker>
            </div>
          </el-form-item>

          <el-form-item>
            <el-button style="width: 150px;" type="primary" @click="onModify">修改</el-button>
            <el-button style="width: 150px" v-on:click="clearall">清空</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "ManageModifyUser",
      data(){
        return{
          username:'',
          userinfo: {
            phone: '',
            options: [{
              value: '男',
              label: '男'
            }, {
              value: '女',
              label: '女'
            }],
            gender: '',
            pickerOptions: {
              disabledDate(time) {
                return time.getTime() > Date.now();
              },
              shortcuts: [{
                text: '今天',
                onClick(picker) {
                  picker.$emit('pick', new Date());
                }
              }, {
                text: '昨天',
                onClick(picker) {
                  const date = new Date();
                  date.setTime(date.getTime() - 3600 * 1000 * 24);
                  picker.$emit('pick', date);
                }
              }, {
                text: '一周前',
                onClick(picker) {
                  const date = new Date();
                  date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                  picker.$emit('pick', date);
                }
              }]
            },
            birth: '2020-03-31T16:00:00.000Z',
          }
        }
      },
      created() {
          var _this = this;
          _this.username = _this.$route.query.username;
          var formData = new FormData();
          formData.append("username",_this.username);
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          this.axios.post("/finduserbyname",formData,config).then(function (response) {
          _this.userinfo.phone = response.data.phone;
          _this.userinfo.gender = response.data.gender;
          _this.userinfo.birth = response.data.birth;
        }).catch(function (error) {
          console.log(error);
        })
      },
      methods:{
        onModify:function () {
          var _this = this;
          var formData = new FormData();
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          formData.append('username',_this.username);
          formData.append('gender',_this.userinfo.gender);
          formData.append('birth',_this.userinfo.birth);
          formData.append('phone',_this.userinfo.phone);
          this.axios.post("/updateuserbyname",formData,config).then(function (response) {
            if(response.data === "ok"){
              alert("修改成功")
              _this.$router.push('/manageuser');
            }
          }).catch(function (error) {
            console.log(error)
          })
        },
        clearall:function () {
          this.userinfo.phone = '';
          this.userinfo.birth = '';
          this.userinfo.gender = '';
        }
      }
    }

</script>

<style scoped>
  #infomodify {
    width: 400px;
    height: 100px;
    margin: 0 auto;
  }
</style>
