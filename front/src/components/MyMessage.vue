<template>
    <div>
      <el-tabs :tab-position="tabPosition" style="height: 600px;" @tab-click="setname">
        <el-tab-pane v-for="(item , index) in chathistory" :key="index" :label="item.messagefrom">
          <div id="box" style="height: 550px;border-style: solid;width: 800px;margin: 0 auto" >
            <div id="box01" style="float: left;overflow: auto;border-style: solid;width: 90%;height: 400px;margin-left: 35px;margin-top: 15px" v-html="item.messageall">{{item.messageall}}</div>
            <input style="width: 550px;height:30px;float: left;margin-left: 35px;margin-top: 25px" type="text" id="txt" placeholder="请输入消息" v-model="inputval"/>
            <el-button type="primary" style="float: right;margin-right: 40px;margin-top: 25px;width: 120px" @click="sendmsg(item.messagefrom,inputval)" @keyup.enter.native="sendmsg(item.messagefrom,inputval)">发送</el-button>
            <el-link type="info" style="float: left;margin-left:35px;margin-top: 10px;" @click="clearall(item.messagefrom)">清空</el-link>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
</template>

<script>
    export default {
        name: "MyMessage",
        data() {
          return {
            tabPosition: 'left',
            inputval:'',
            name:'',
            chathistory:[
              {messageall:'',messagefrom:'',messageto:''}
            ]
          };
        },
      created() {
        var _this = this;
        //绑定一下键盘的监听事件

        document.onkeydown = function(e){
          var key = window.event.keyCode;
          if(key == 13){
            _this.sendmsg();
          }
        };

        //移出第一个聊天对象（null）
        _this.chathistory.pop()
        //定时刷新任务
        _this.timer = setInterval(()=>{

        },1000)

        //查看是谁给我发送了消息
        var formData = new FormData();
        formData.append("messageto",_this.$store.state.username)
        let config = {
          headers:{"Content-Type":"multipart/form-data"}
        };
        _this.axios.post('/findchathistoryforme',formData,config).then(function (response) {
          console.log(response);
          //找到和我说话的人有哪些
          var messagefromlist = [];
          for (var i = 0 ; i < response.data.length ; ++i){
            if(messagefromlist.includes(response.data[i].messagefrom) === false && response.data[i].messageto === _this.$store.state.username){
              messagefromlist.push(response.data[i].messagefrom);
            }
          }
          for(var i = 0 ; i < messagefromlist.length ;++i){
            var obj = {messageall:'',messagefrom:messagefromlist[i],messageto:_this.$store.state.username}
            _this.chathistory.push(obj);
          }
          for(var i = 0 ; i < _this.chathistory.length ; ++i){
            for (var j = 0 ; j < response.data.length ; ++j){
              if (response.data[j].messagefrom === _this.chathistory[i].messagefrom && response.data[j].messageto === _this.$store.state.username){
                _this.chathistory[i].messageall += '<p style="text-align:left;width: 60%;float:left;margin:5px 20px">' + response.data[j].record + '</p>';
                _this.$nextTick(() => {
                  if(document.getElementById('box01') !== null){
                    document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
                  }
                })
              }else if(response.data[j].messageto === _this.chathistory[i].messagefrom && response.data[j].messagefrom === _this.$store.state.username){
                _this.chathistory[i].messageall += '<p style="text-align: right;width: 60%;float: right;margin: 5px 20px">' + response.data[j].record + '</p>'
                _this.$nextTick(() => {
                  if(document.getElementById('box01') !== null){
                    document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
                  }
                })
              }
            }
          }
        }).catch(function (error) {
          console.log(error);
        })
      },
      methods:{
          sendmsg:function (messageto,val) {
            var _this = this;
            _this.inputval = '';
            //储存现在刚刚的聊天记录,存的比较慢
            // var val = document.getElementById('txt1').value;
            var formData = new FormData();
            formData.append('messagefrom',_this.$store.state.username);
            formData.append('messageto',messageto);
            formData.append('record',val);
            let config = {
              headers:{"Content-Type":"multipart/form-data"}
            };
            if(val !== ''){
              _this.axios.post('/storechathistory',formData,config).then(function (response) {
                /*Vue 实现响应式并不是数据发生变化之后 DOM 立即变化，而是按一定的策略进行 DOM 的更新。
                 $nextTick 是在下次 DOM 更新循环结束之后执行延迟回调，在修改数据之后使用 。*/
                _this.$nextTick(() => {
                  document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
                })
                document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
              }).catch(function (error) {
                console.log(error);
              });
            }else if(val === ''){
              this.$message({message: '请输入内容发送！', type: 'warning'});
            }
            _this.inputval = '';
            // this.oTxt.value=""; //发送完成侯情况输入框
          },
        setname:function (name) {
            var _this = this;
            _this.name = name.label;
            var index = 0;
            //找到现在是在哪个对话框
            for (var i = 0 ; i < _this.chathistory.length ;++i){
              if(_this.chathistory[i].messagefrom === _this.name){
                index = i;
                break;
              }
            }
          _this.timer = setInterval(()=>{
            var formData = new FormData();
            formData.append('messagefrom',_this.name);
            formData.append('messageto',_this.$store.state.username);
            let config ={
              headers:{"Content-Type":"multipart/form-data"}
            };
            _this.axios.post('/findchathistory',formData,config).then(function (response) {
              //重新更新信息列表
              _this.chathistory[index].messageall = '';
              for(var i = 0 ; i < response.data.length ;++i){
                if(response.data[i].messagefrom === _this.name && response.data[i].messageto === _this.$store.state.username){
                  _this.chathistory[index].messageall += '<p style="text-align:left;width: 60%;float:left;margin:5px 20px">' + response.data[i].record + '</p>';
                  _this.$nextTick(() => {
                    document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
                  })
                }else if(response.data[i].messagefrom === _this.$store.state.username && response.data[i].messageto === _this.name){
                  _this.chathistory[index].messageall += '<p style="text-align:right;width: 60%;float:right;margin:5px 20px">' + response.data[i].record + '</p>';
                  _this.$nextTick(() => {
                    document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
                  })
                }
              }
            }).catch(function (error) {
              console.log(error);
            })
          },1000)

        },
        clearall:function (messagefrom) {
          var _this = this;
          this.$confirm('此操作将永久删除该聊天记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            var formData = new FormData();
            formData.append('messagefrom',messagefrom);
            formData.append('messageto',_this.$store.state.username);
            let config = {
              headers:{"Content-Type":"multipart/form-data"}
            };
            _this.axios.post('/deletechathistories',formData,config).then(function (response) {
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
      },
      beforeDestroy() {
        clearInterval(this.timer);
      }
    }
</script>

<style scoped>

</style>
