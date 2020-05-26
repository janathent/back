<template>
  <div>
    <span style="float: left;width: 100%">聊天室测试</span>
    <div id="box1" style="height: 550px;width: 48%;border-style: solid;float:left;">
      <div id="box01" style="float: left;overflow: auto;border-style: solid;width: 90%;height: 400px;margin-left: 35px;margin-top: 10px" v-html="data1">{{data1}}</div>
      <input style="width: 500px;height:30px;float: left;margin-left: 35px;margin-top: 25px" type="text" id="txt1" placeholder="请输入消息" />
      <el-button type="primary" style="float: right;margin-right: 40px;margin-top: 25px;width: 120px" @click="msg1" @keyup.enter.native="msg1">发送</el-button>
      <el-link type="info" style="float: left;margin-left:35px;margin-top: 10px;" @click="clearmsg">清空</el-link>
    </div>
    <div id="box2" style="height: 550px;width: 48%;border-style: solid;float:right;">
      <div id="box02" style="float: left;overflow: auto;width: 90%;height: 400px;margin-left: 35px;border-style: solid;margin-top: 10px" v-html="data2">{{data2}}</div>
      <input style="width: 500px;height:30px;float: left;margin-left: 35px;margin-top: 25px" type="text" id="txt2" placeholder="请输入消息" />
      <el-button type="primary" style="float: right;margin-right: 40px;margin-top: 25px;width: 120px" @click="">发送</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return{
        id1:'1',
        id2:'2',
        data1:'',
        data2:'',
        messagefrom:'' ,  //发送方
        messageto:'' //接收方
      }
    },
    watch:{

    },
    created() {
      var _this = this;
      //绑定一下键盘的监听事件
      document.onkeydown = function(e){
        var key = window.event.keyCode;
        if(key == 13){
          _this.msg1();
        }
      };


      //实现用户发送者与接受者的名字赋值
      _this.messagefrom = _this.$route.query.messagefrom;
      _this.messageto = _this.$route.query.messageto;

      var formData = new FormData();
      formData.append('messagefrom',_this.messagefrom);
      formData.append('messageto',_this.messageto);
      let config ={
        headers:{"Content-Type":"multipart/form-data"}
      };
      _this.axios.post('/findchathistory',formData,config).then(function (response) {
        for(var i = 0 ; i < response.data.length ;++i){
          var chatcontent = response.data[i].record;
          var messagefrom = response.data[i].messagefrom;
          var messageto = response.data[i].messageto;
          if(messagefrom === _this.messagefrom && messageto === _this.messageto){
            _this.data1 += '<p style="text-align:right;width: 60%;float:right;margin:5px 20px">' + chatcontent + '</p>';
          }else if(messagefrom === _this.messageto && messageto === _this.messagefrom){
            _this.data1 += '<p style="text-align:left;width: 60%;float:left;margin:5px 20px">' + chatcontent + '</p>';
          }
        }
      }).catch(function (error) {
        console.log(error);
      })
    }
    ,
    methods:{
      msg1:function () {
        var _this = this;
        //储存现在刚刚的聊天记录,存的比较慢
        var val = document.getElementById('txt1').value;
        var formData1 = new FormData();
        formData1.append('messagefrom',_this.messagefrom);
        formData1.append('messageto',_this.messageto);
        formData1.append('record',val);
        let config1 = {
          headers:{"Content-Type":"multipart/form-data"}
        };
        if(val !== ''){
          _this.axios.post('/storechathistory',formData1,config1).then(function (response) {
            _this.data1 += '<p style="text-align:right;width: 60%;float:right;margin:5px 20px">' + val + '</p>';
            /*Vue 实现响应式并不是数据发生变化之后 DOM 立即变化，而是按一定的策略进行 DOM 的更新。
             $nextTick 是在下次 DOM 更新循环结束之后执行延迟回调，在修改数据之后使用 。*/
            _this.$nextTick(() => {
              document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
            })
            document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
            console.log(response);
          }).catch(function (error) {
            console.log(error);
          });
        }else if(val === ''){
          this.$message({message: '请输入内容发送！', type: 'warning'});
        }
        document.getElementById('txt1').value = '';
        // this.oTxt.value=""; //发送完成侯情况输入框
      },
      clearmsg:function () {
        var _this = this;
        this.$confirm('此操作将永久删除该聊天记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.data1 =  '';
          var formData = new FormData();
          formData.append('messagefrom',_this.messagefrom);
          formData.append('messageto',_this.messageto);
          let config = {
            headers:{"Content-Type":"multipart/form-data"}
          };
          _this.axios.post('/deletechathistoriesbyid',formData,config).then(function (response) {
            console.log(response);
          }).catch(function (error) {
            console.log(error);
          })
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    }
  }
</script>
<style scoped>

</style>
