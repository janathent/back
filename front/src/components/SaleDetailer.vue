<template>
  <div id="main">
    <!--    头部-->
    <div id="head" style="height:65px;background-color: #99a9bf">
      <div id="headleft" style="font-size:30px;float:left;margin-left: 20px;margin-top: 20px;"><strong>房屋交易平台</strong></div>
      <div id="search" style="float: left; width: 300px; margin-left: 200px; margin-top:20px">
        <el-input v-model="input" placeholder="搜索房源"></el-input>
      </div>
      <el-button icon="el-icon-search" circle style="float: left ;margin-top: 20px ;margin-left: 10px"></el-button>
      <div id="button" style="float: right ; margin-right: 10px ; margin-top: 20px"><el-button  type="success" @click="fabu">立刻发布</el-button></div>
    </div>
    <div style="height: 20px;background-color: #99a9bf">
      <div style="font-size: 10px;float:left;margin-left: 20px"><span>当前位置：房屋详细</span></div>
    </div>
    <!--    图片显示部分-->
    <div id="picleft" style="height: 350px ;width:50%;float: left" @click="clearoverflow">
      <!--      为了好看，加个小框框-->
      <div id="pic" style="height: 320px ;width: 95%;margin-top: 15px;margin-left: 12px">
        <el-carousel indicator-position="outside">
          <el-carousel-item v-for="(item,index) in urls" :key="index">
            <h3><div class="demo-image">
              <div class="block">
                <el-image :src="'../../static/housepic/' + item" :fit="fit"></el-image>
              </div>
            </div></h3>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <!--    图片的右边详情-->
    <div id="picright" style="height: 350px ;width:50%;float: left">
      <div id="price" style="height: 50px "><span style="color: red;font-size: 45px">{{price}}</span><span style="color: red">万</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 12px">{{priceper}}</span><span style="font-size: 12px">万元/平</span><div style="height: 50px;float: right;background-color: forestgreen"></div></div>
      <div id="square" style="height: 40px;float: left;margin-left: 30px;margin-top: 10px"><span style="font-size: 30px">{{square}}</span><span style="font-size: 25px">平方米</span></div>
      <div id="shiweiting" style="height: 40px;float:left;margin-top: 10px;margin-left: 50px"><span style="font-size: 25px">{{shivalue}}</span><span style="font-size: 25px">室</span><span style="font-size: 25px">{{weivalue}}</span><span style="font-size: 25px">卫</span><span style="font-size: 25px">{{tingvalue}}</span><span style="font-size: 25px">厅</span></div>
      <div id="housetype" style="height: 40px;float:left;margin-top: 10px;margin-left: 50px"><span style="font-size: 25px">{{housetypevalue}}</span></div>
      <div id="location" style="text-align: left;float:left;margin-left:30px;margin-top:10px;height: 30px;width: 80%"><span style="font-size: 15px">房屋地址:</span><span style="font-size: 15px">{{loaction}}</span></div>
      <div id="decoration" style="text-align: left;float:left;margin-left:30px;margin-top:10px;height: 30px;width: 80%"><span style="font-size: 15px">装修情况:</span><span style="font-size: 15px">{{decorationvalue}}</span></div>
      <div id="heat" style="text-align: left;float:left;margin-left:30px;margin-top:10px;height: 30px;width: 80%"><span style="font-size: 15px">供暖情况:</span><span style="font-size: 15px">{{heatvalue}}</span></div>
      <div id="propertyrightstype" style="text-align: left;float:left;margin-left:30px;margin-top:10px;height: 30px;width: 80%"><span style="font-size: 15px">产权类型:</span><span style="font-size: 15px">{{propertyrightstypevalue}}</span></div><div style="float: left;height: 50px"@click="collection"><span ><i style="font-size: 30px" v-show="isshoucangvalue === true" class="el-icon-star-on"></i><i style="font-size: 30px" v-show="isshoucangvalue === false" class="el-icon-star-off"></i></span><br><span style="font-size: 12px">收藏</span></div>
      <div id="propertyrightsyear" style="text-align: left;float:left;margin-left:30px;margin-top:10px;height: 30px;width: 80%"><span style="font-size: 15px">产权年限:</span><span style="font-size: 15px">{{propertyrightsyearvalue}}</span></div>
      <div id="phoneconn" style="float:left;margin-left:30px;margin-top:5px;height: 35px;width: 50%"><i class="el-icon-phone"></i><el-button style="height: 35px ; width: 180px" type="primary" plain @click="drawer = true">电话联系</el-button></div>
      <div id="roomconn" style="float:left;margin-left:25px;margin-top:5px;height: 35px;width: 25%"><i class="el-icon-s-comment"></i><el-button style="height: 35px" type="info" plain @click="chatroom">聊天室</el-button></div>
    </div>

<!--    聊天室-->
    <el-dialog :title="messageto" :visible.sync="dialogTableVisible" @close="closedia">
      <div id="box1" style="height: 540px;width: 700px;border-style: solid;margin: 0 auto">
        <div id="box01" style="float:left;overflow: auto;border-style: solid;width: 90%;height: 400px;margin-left: 35px;margin-top: 15px" v-html="chatall">{{chatall}}</div>
        <input style="width: 500px;height:30px;float: left;margin-left: 35px;margin-top: 25px" type="text" id="txt1" placeholder="请输入消息" />
        <el-button type="primary" style="float: right;margin-right: 40px;margin-top: 25px;width: 100px" @click="sendmsg" @keyup.enter.native="sendmsg">发送</el-button>
        <el-link type="info" style="float: left;margin-left:35px;margin-top: 10px;" @click="clearmsg">清空</el-link>
      </div>
    </el-dialog>



    <!--    概况-->
    <div id="survey" style="height: 270px;width: 100%;float: left">
      <div style="height: 40px;float:left;width: 100%;text-align: left"><strong style="margin-left: 20px;font-size: 25px"><i class="el-icon-caret-right"></i>概况</strong></div>
      <div style="float:left; width: 300px;height:30px;margin-left: 20px;margin-top: 20px;text-align:left"><span style="font-size: 15px">房屋总价</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{price}}万</span></div>
      <div style="float:left; width: 300px;height:30px;margin-left: 80px;margin-top: 20px;text-align:left"><span style="font-size: 15px">房屋户型</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{shivalue}}室{{tingvalue}}厅{{weivalue}}卫</span></div>
      <div style="float:left; width: 300px;height:30px;margin-left: 20px;margin-top: 20px;text-align:left"><span style="font-size: 15px">房屋朝向</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{directionvalue}}</span></div>
      <div style="float:left; width: 300px;height:30px;margin-left: 80px;margin-top: 20px;text-align:left"><span style="font-size: 15px">房屋层数</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{layernumbervalue}}</span></div>
      <div style="float:left; width: 300px;height:30px;margin-left: 20px;margin-top: 20px;text-align:left"><span style="font-size: 15px">装修情况</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{decorationvalue}}</span></div>
      <div style="float:left; width: 300px;height:30px;margin-left: 80px;margin-top: 20px;text-align:left"><span style="font-size: 15px">电梯情况</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{elevatorvalue}}</span></div>
      <div style="float:left; width: 300px;height:30px;margin-left: 20px;margin-top: 20px;text-align:left"><span style="font-size: 15px">产权类型</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{propertyrightstypevalue}}</span></div>
      <div style="float:left; width: 300px;height:30px;margin-left: 80px;margin-top: 20px;text-align:left"><span style="font-size: 15px">产权年限</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{propertyrightsyearvalue}}</span></div>
    </div>

    <!--描述-->
    <div id="describe" style="height: 300px;width: 50%;float:left;">
      <div style="height: 40px;float:left;width: 100%;text-align: left"><strong style="margin-left: 20px;font-size: 25px"><i class="el-icon-caret-right"></i>描述</strong></div>
      <div style="float:left; width: 80px;height:30px;margin-left: 20px;margin-top: 20px;text-align:left;"><el-tag effect="dark">房屋信息</el-tag></div>
      <div style="float:left;height: 100px;width: 300px;margin-left: 50px;margin-top: 20px;text-align: left">
        <span style="word-break:break-all;">{{title}}</span>
      </div>

      <div style="float:left; width: 80px;height:30px;margin-left: 20px;margin-top: 20px;text-align:left"><el-tag effect="dark">房屋优点</el-tag></div>
      <div style="float:left;height: 100px;width: 300px;margin-left: 50px;margin-top: 20px;text-align: left">
        <span style="word-break:break-all;">{{feature}}</span>
      </div>
    </div>



    <!--房屋动态-->
    <div id="dongtai" style="width: 100%;float:left;">
      <div style="height: 40px;float:left;width: 100%;text-align: left"><strong style="margin-left: 20px;font-size: 25px"><i class="el-icon-caret-right"></i>房源动态</strong></div>
      <div style="float:left; width: 90%;height:30px;margin-left: 20px;margin-top: 20px;text-align:left;"><span style="font-size: 15px">浏览人数</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">200万</span></div>
      <div style="float:left; width: 160px;margin-left: 20px;margin-top: 20px">
        <el-timeline :reverse="reverse">
          <el-timeline-item v-for="(activity, index) in activities" :key="index" :timestamp="activity.timestamp">{{activity.content}}</el-timeline-item>
        </el-timeline>
      </div>
    </div>


    <!--    费用-->
    <div id="fee" style="height: 300px;width: 100%;float:left;">
      <div style="height: 40px;float:left;width: 100%;text-align: left"><strong style="margin-left: 20px;font-size: 25px"><i class="el-icon-caret-right"></i>房源费用</strong></div>
      <div style="float:left; width: 80%;height:30px;margin-left: 20px;margin-top: 20px;text-align:left;"><span style="font-size: 15px">房屋总价</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{price}}</span></div>
      <div style="float:left; width: 80%;height:30px;margin-left: 20px;margin-top: 20px;text-align:left;"><span style="font-size: 15px">房屋单价</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">200万</span></div>
      <div style="float:left; width: 80%;height:30px;margin-left: 20px;margin-top: 20px;text-align:left;"><span style="font-size: 15px">房屋类型</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{housetypevalue}}</span></div>
      <div style="float:left; width: 80%;height:30px;margin-left: 20px;margin-top: 20px;text-align:left;"><span style="font-size: 15px">交易权属</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">{{propertyrightstypevalue}}</span></div>
    </div>

    <!--大图-->
    <div id="huxing" style="width: 100%;float:left;">
      <div style="height: 40px;float:left;width: 100%;text-align: left"><strong style="margin-left: 20px;font-size: 25px"><i class="el-icon-caret-right"></i>图片详情</strong></div>
      <div id="picture" style="float:left;margin-left: 20%; width: 60%;">
        <div class="demo-image__lazy">
          <el-image v-for="url in urls" :key="url" :src="'../../static/housepic/' + url" lazy></el-image>
        </div>
      </div>

    </div>


    <!--    推荐-->
    <div id="recommand" style="height: 300px;width: 100%;background-color: blanchedalmond;float:left;">
      <div style="height: 40px;float:left;width: 100%;background-color: pink;text-align: left"><strong style="margin-left: 20px;font-size: 25px"><i class="el-icon-caret-right"></i>推荐</strong></div>
      <div style="float:left; width: 80%;height:50px;margin-left: 20px;margin-top: 20px;text-align:left;background-color: #3385FF"><span style="font-size: 15px">推荐模块</span>&nbsp;&nbsp;&nbsp;<span style="font-size: 15px">200万</span></div>
    </div>








    <!--    显示号码的抽屉-->
    <el-drawer title="请拨打下面电话号码" :visible.sync="drawer">
      <span>{{phone}}</span>
    </el-drawer>
  </div>

</template>

<script>
    export default {
        name: "SaleDetailer",
        data() {
          return {
            //搜索框的数据
            input: '',
            urls:[],
            //显示电话
            drawer: false,
            fit:'fill',

            dialogTableVisible:false,


            //房源的更新时间
            reverse: true,
            activities: [{
              content: '活动按期开始',
              timestamp: '2018-04-15'
            }, {
              content: '通过审核',
              timestamp: '2018-04-13'
            }, {
              content: '创建成功',
              timestamp: '2018-04-11'
            },{
              content: '通过审核',
              timestamp: '2018-04-13'
            },{
              content: '通过审核',
              timestamp: '2018-04-13'
            },],

            //模拟的id
            id:'30',
            title:'',
            loaction :'',
            shivalue :'',
            tingvalue:'',
            weivalue :'',
            directionvalue:'',
            layernumbervalue:'',
            layertotalvalue :'',
            decorationvalue :'',
            heatvalue :'',
            elevatorvalue:'',
            housetypevalue:'',
            propertyrightstypevalue:'',
            propertyrightsyearvalue:'',
            square:'',
            price :'',
            feature :'',
            picnumber :'',
            priceper:'',
            //是否收藏
            isshoucangvalue :'' ,
            messagefrom:''  ,//聊天室传用户的名称
            messageto:''  ,//聊天室传用户的名称
            chatall:'', //存储所有的聊天记录
         //   chatnumber:0,  //记录现在在框内的聊天数据条数
            phone:''  //用户电话
          }
        },
      created() {
        document.body.style=null;
        var _this = this;
        var formData = new FormData();
        formData.append("id",this.$route.query.id);
        let config ={
          headers:{"Content-Type":"multipart/form-data"}
        };
        //键盘事件
        document.onkeydown = function(e){
          var key = window.event.keyCode;
          if(key == 13){
            _this.sendmsg();
          }
        };

        var formData1 = new FormData();
        formData1.append('username',_this.$store.state.username);
        formData1.append('collectionid',_this.$route.query.id);
        _this.axios.post('findcollection',formData1,config).then(function (response) {
          if(response.data === true){
            _this.isshoucangvalue = true;
          }else if(response.data === false){
            _this.isshoucangvalue = false;
          }
        }).catch(function (error) {
          console.log(error);
        })

        //查找电话号码
        _this.axios.post('/findPhonebyusername',formData1,config).then(function (response) {
          _this.phone = response.data
        }).catch(function (error) {
          console.log(error);
        })

        //实现浏览记录存储
        if(_this.$store.state.username !== "请登录"){
          var formData2 = new FormData();
          var currenttime = new Date();
          formData2.append('username',_this.$store.state.username);
          formData2.append('browseid',_this.$route.query.id);
          formData2.append('time',currenttime.toLocaleString());
          _this.axios.post('/storebrowse',formData2,config).then(function (response) {
          }).catch(function (error) {
            console.log(error);
          })
        }

        this.axios.post("/salemodifyshow",formData,config).then(function (response) {
          _this.title = response.data[0].title;
          _this.loaction = response.data[0].loaction;
          _this.shivalue = response.data[0].shi;
          _this.tingvalue = response.data[0].ting;
          _this.weivalue = response.data[0].wei;
          _this.directionvalue = response.data[0].direction;
          _this.layernumbervalue = response.data[0].layernumber;
          _this.layertotalvalue = response.data[0].layertotal;
          _this.decorationvalue = response.data[0].decoration;
          _this.heatvalue = response.data[0].heat;
          _this.elevatorvalue = response.data[0].elevator;
          _this.housetypevalue = response.data[0].housetype;
          _this.propertyrightstypevalue = response.data[0].propertyrightstype;
          _this.propertyrightsyearvalue = response.data[0].propertyrightsyear;
          _this.square = response.data[0].square;
          _this.price = response.data[0].price;
          _this.feature = response.data[0].feature;
          _this.picnumber = response.data[0].picnumber;
          _this.messageto = response.data[0].username;  //向谁发送数据
          _this.messagefrom = _this.$store.state.username;
          _this.priceper = (_this.price / _this.square).toFixed(2);


          // //处理收藏
          // var collectionarray = response.data[0].collection;
          // collectionarray = collectionarray.substr(0,collectionarray.length - 1);
          // collectionarray = collectionarray.split('#');
          // for(var i = 0 ; i < collectionarray.length ;++i){
          //   if(collectionarray[i] === _this.$route.query.id){
          //     _this.isshoucang = true;
          //   }
          // }

          //处理图片路径
          var listtemp = response.data[0].picpath.substr(0,response.data[0].picpath.length - 1);
          listtemp = listtemp.split("#");
          for(var i = 0 ; i < listtemp.length ;++i){
            var pos = listtemp[i].lastIndexOf("\\");
            listtemp[i] = listtemp[i].substr(pos + 1 ,listtemp[i].length - 1)
            _this.urls.push(listtemp[i]);
          }
        }).catch(function (error) {
          console.log(error);
        })



      },
      methods:{
        fabu:function () {
          if(this.$store.state.username === '请登录'){
            this.$router.push("/login");
          }else{
            this.$router.push('/saleupload')
          }
        },
        //实现收藏功能
        collection:function () {
          var _this = this;
          if(_this.$store.state.username === "请登录"){
            alert("请先登录！");
            return;
          }
          if(_this.isshoucangvalue === true){
            _this.isshoucangvalue = false;
            //实现取消收藏
            var formData = new FormData();
            formData.append('username',_this.$store.state.username);
            formData.append('collectionid',_this.$route.query.id);
            let config ={
              headers:{"Content-Type":"multipart/form-data"}
            };
            _this.axios.post('/cancelcollection',formData,config).then(function (response) {
              console.log(response)
            }).catch(function (error) {
              console.log(error)
            })
          }else if(_this.isshoucangvalue === false){
            _this.isshoucangvalue = true;
            //实现新增收藏
            var formData = new FormData();
            formData.append('username',_this.$store.state.username);
            formData.append('collectionid',_this.$route.query.id);
            let config = {
              headers:{"Content-Type":"multipart/form-data"}
            };
            _this.axios.post('/addcollection',formData,config).then(function (response) {
              console.log(response)
            }).catch(function (error) {
              console.log(error)
            })

          }
        },

        //解决图片点击就卡死的BUG
       clearoverflow:function () {
          document.body.style=null;
       },

        //弹出聊天室
        chatroom:function () {
          var _this = this;
          //_this.$router.push({path:'test',query:{messagefrom:_this.$store.state.username,messageto:_this.messageto}});
          if(_this.messagefrom === _this.messageto){
            alert("不能与自己聊天");
            return;
          }
          if(_this.$store.state.username === "请登录"){
            alert("请先登录！");
            _this.$router.push('/login');
            return;
          }
          var formData = new FormData();
          formData.append('messagefrom',_this.messagefrom);
          formData.append('messageto',_this.messageto);
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          //发送请求查询聊天记录显示在对话框上面
          _this.axios.post('/findchathistory',formData,config).then(function (response) {
           // _this.chatnumber = response.data.length;
            for(var i = 0 ; i < response.data.length ;++i){
              var chatcontent = response.data[i].record;
              var messagefrom = response.data[i].messagefrom;
              var messageto = response.data[i].messageto;
              if(messagefrom === _this.messagefrom && messageto === _this.messageto){
                _this.chatall += '<p style="text-align:right;width: 60%;float:right;margin:5px 20px">' + chatcontent + '</p>';
              }else if(messagefrom === _this.messageto && messageto === _this.messagefrom){
                _this.chatall += '<p style="text-align:left;width: 60%;float:left;margin:5px 20px">' + chatcontent + '</p>';
              }
            }
          }).catch(function (error) {
            console.log(error);
          })
          _this.dialogTableVisible = true;  //窗体进行显示
          //启动定时器进行聊天记录的刷新
          if(_this.dialogTableVisible === true){
            _this.timer = setInterval(()=>{
              _this.chatall = '';
              var formData = new FormData();
              formData.append('messagefrom',_this.messagefrom);
              formData.append('messageto',_this.messageto);
              let config ={
                headers:{"Content-Type":"multipart/form-data"}
              };
              _this.axios.post('/findchathistory',formData,config).then(function (response) {
                for(var i = 0  ; i < response.data.length ;++i){
                  var chatcontent = response.data[i].record;
                  var messagefrom = response.data[i].messagefrom;
                  var messageto = response.data[i].messageto;
                  if(messagefrom === _this.messagefrom && messageto === _this.messageto){
                    _this.chatall += '<p style="text-align:right;width: 60%;float:right;margin:5px 20px">' + chatcontent + '</p>';
                    _this.$nextTick(() => {
                      document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
                    })
                  }else if(messagefrom === _this.messageto && messageto === _this.messagefrom){
                    _this.chatall += '<p style="text-align:left;width: 60%;float:left;margin:5px 20px">' + chatcontent + '</p>';
                    _this.$nextTick(() => {
                      document.getElementById('box01').scrollTop = document.getElementById('box01').scrollHeight;
                    })
                  }
                }
              }).catch(function (error) {
                console.log(error);
              })
            },1000)
          }
        },
        //关闭窗口的时候清掉定时器
        closedia:function () {
          var _this = this;
          _this.dialogTableVisible = false;
          clearInterval(_this.timer)
        },
        sendmsg:function () {
          var _this = this;
          var val = document.getElementById('txt1').value;
          if(val !== ''){
            var formData = new FormData();
            formData.append('messagefrom',_this.messagefrom);
            formData.append('messageto',_this.messageto);
            formData.append('record',val);
            let config ={
              headers:{"Content-Type":"multipart/form-data"}
            };
            _this.axios.post('/storechathistory',formData,config).then(function (response) {
              console.log(response)
            }).catch(function (error) {
              console.log(error);
            })
          }else if(val === ''){
            _this.$message({message: '请输入内容发送！', type: 'warning'});
          }
          document.getElementById('txt1').value = '';
        },

        //清空聊天记录
        clearmsg:function () {
          var _this = this;
          this.$confirm('此操作将永久删除该聊天记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            var formData = new FormData();
            formData.append('messagefrom',_this.messagefrom);
            formData.append('messageto',_this.messageto);
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
      // mounted() {
      //     if(this.timer){
      //       clearInterval(this.timer)
      //     }else if(!this.timer){
      //       this.timer = setInterval(()=>{
      //         console.log(3213213)
      //       },1000)
      //     }
      // },
      // destroyed() {
      //     clearInterval(this.timer);
      // }

    }
</script>

<style scoped>

  #main{
    width: 1000px;
    margin: 0 auto;
    /*background-color: #d3dce6;*/
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
