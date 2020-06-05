<template>

<div class="container">
  <div id="wrap">
    <form>
      <input @click="searchfun" type="submit" id="sub" value="搜索"/>
      <input v-model="searchitem" type="text" id="text" value=""/>
    </form>
  </div>
  <ul>
    <li v-for="(item,index) in list" :key="index">
      <div class="test">
        <div class="picture">
          <div class="demo-image">
            <div class="block">
              <router-link title="点击查看详情" :to="{path: 'saledetailer', query: {id:item.id}}"><el-image style="width: 150px; height: 150px" :src="'../../static/housepic/' + item.picpath"></el-image></router-link>
            </div>
          </div>
        </div>
        <div class="bigtitle"><strong style="font-size: 18px">{{item.title}}</strong></div>
        <div class="line1"><span class="linefontsize">{{item.shi}}室{{item.ting}}厅{{item.wei}}卫</span>&nbsp;&nbsp;<span class="linefontsize">{{item.square}}㎡</span>&nbsp;&nbsp;<span class="linefontsize">{{item.direction}}</span>&nbsp;&nbsp;<span class="linefontsize">{{item.layernumber}}层(共{{item.layertotal}}层)</span></div>
        <div class="price1"><span class="price11">{{item.price}}</span><span>万</span><br><span style="font-size: 12px">{{(item.price / item.square).toFixed(2)}}</span><span style="font-size: 12px">万元/平</span></div>
        <div class="line2"><span class="linefontsize">{{item.loaction}}</span></div>
        <div class="line3"><span class="linefontsize">{{item.username}}</span></div>
      </div>
    </li>
  </ul>
</div>

</template>

<script>
    export default {
        name: "ShowMain",
      data() {
        return {
          currentDate: new Date(),
          url: '../../static/house.jpg',
          urls:[],
          list:'',
          test:'',
          //搜索的内容
          searchitem:''
        };
      },
      created() {
          this.init();
      },
      methods:{
          init: function () {
            var _this = this;
            this.axios.post('/saleshow').then(function (response) {
              console.log(response)
              _this.list = response.data;
            //  "D:\HouseTransactions\graduate\front\static\housepic\黄和龙_2_0.JPG#"
              for(var i = 0 ; i < response.data.length ;++i){
                var pos = response.data[i].picpath.lastIndexOf("\\");
                // _this.urls.push();
                _this.list[i].picpath = response.data[i].picpath.substr(pos + 1,response.data[i].picpath.length - pos - 2);
              }
            }).catch(function (error) {
                console.log(error);
            })
          },
        searchfun:function () {
            var _this = this;
            if(_this.searchitem === ''){
              alert("请正确输入");
              return;
            }else{
              var formData = new FormData();
              formData.append('searchitem',_this.searchitem);
              let config ={
                headers:{"Content-Type":"multipart/form-data"}
              };
              _this.axios.post('/searchshowmain',formData,config).then(function (response) {
                if(response.data.length === 0){
                  alert("没有符合结果的房屋");
                  return;
                }else {
                  _this.list = response.data;
                  for(var i = 0 ; i < response.data.length ;++i){
                    var pos = response.data[i].picpath.lastIndexOf("\\");
                    // _this.urls.push();
                    _this.list[i].picpath = response.data[i].picpath.substr(pos + 1,response.data[i].picpath.length - pos - 2);
                  }
                }

              }).catch(function (error) {
                console.log(error)
              })
            }
        }
      }
    }
</script>

<style scoped>
  /**{*/
  /*  margin: 0;*/
  /*  padding:0;*/
  /*}*/
.test{
  width: 1000px;
  height: 180px;
  margin: 0 auto;
  /*background-color: lightgrey;*/
}
.test:hover{
  background-color: lightgrey;
}

  .picture{
    float: left;
    width: 150px;
    height: 150px;
    margin-top: 15px;
    margin-left: 15px;
    background-color: aqua;
  }
  .bigtitle{
    float: left;
    width: 600px;
    height: 25px;
    margin-top: 15px;
    margin-left: 15px;
    /*background-color: darkcyan;*/
    text-align: left;
  }
  .line1{
    float: left;
    height: 25px;
    width: 500px;
    margin-top: 15px;
    margin-left: 15px;
    text-align: left;
    /*background-color: darkcyan;*/
  }
.line2{
  float: left;
  height: 25px;
  width: 500px;
  margin-top: 15px;
  margin-left: 15px;
  text-align: left;
  /*background-color: darkcyan;*/
}
.line3{
  float: left;
  height: 25px;
  width: 500px;
  margin-left: 15px;
  margin-top: 15px;
  text-align: left;
  /*background-color: darkcyan;*/
}
.price1{
  float: right;
  height: 70px;
  width: 100px;
  margin-right: 50px;
  /*background-color: crimson;*/
}
.linefontsize{
  font-size: 10px;
}
.price11{
  font-size: 40px;
  color: crimson;
}
ul li{
  list-style-type: none;
}

  #wrap{
    width: 100%;
    height: 55px;
    background-color: #eee;
    border: solid 1px #eee;
    position: sticky;
    top: 0px;
    z-index: 1000;
  }

  #text{
    width: 300px;
    height: 30px;
    display: block;
    float: right;
    margin-right: 5px;
    margin-top: 10px;
    border-radius: 15px;
    border-style: none;
  }
  #sub{
    width: 80px;
    height: 30px;
    display: block;
    float: right;
    margin-top: 12px;
    margin-right: 130px;
    background-color: #3385FF;
    border: solid 1px #3385FF;
    color: white;
    cursor: pointer;
    border-radius: 15px;
  }
</style>
