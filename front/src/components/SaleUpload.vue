<template>
<!--    卖家上传信息-->
  <div class="container">
    <div id="upload">
      <el-form ref="form" :model="form" label-width="80px">

        <el-form-item label="标题" >
          <el-input v-model="form.title" placeholder="自定义一个房屋的标题"></el-input>
        </el-form-item>

        <el-form-item label="房屋地址" >
          <el-input v-model="form.loaction" placeholder="填写住宅小区名"></el-input>
        </el-form-item>

        <el-row :gutter="30">
          <el-col :span="8">
            <el-form-item label="几室" >
              <el-select v-model="form.shivalue" placeholder="请选择">
                <el-option v-for="item in form.shi" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="几厅" >
              <el-select v-model="form.tingvalue" placeholder="请选择">
                <el-option v-for="item in form.ting" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="几卫" >
              <el-select v-model="form.weivalue" placeholder="请选择">
                <el-option v-for="item in form.wei" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row :gutter="30">
          <el-col :span="8">
            <el-form-item label="朝向" >
              <el-select v-model="form.directionvalue" placeholder="请选择">
                <el-option v-for="item in form.direction" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="第几层" >
              <el-select v-model="form.layernumbervalue" placeholder="请选择">
                <el-option v-for="item in form.layernumber" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="共几层" >
              <el-select v-model="form.layertotalvalue" placeholder="请选择">
                <el-option v-for="item in form.layertotal" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>



        <el-row :gutter="30">
          <el-col :span="8">
            <el-form-item label="装修" >
              <el-select v-model="form.decorationvalue" placeholder="装修情况">
                <el-option v-for="item in form.decoration" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供暖" >
              <el-select v-model="form.heatvalue" placeholder="供暖情况">
                <el-option v-for="item in form.heat" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电梯" >
              <el-select v-model="form.elevatorvalue" placeholder="电梯情况">
                <el-option v-for="item in form.elevator" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="30">
          <el-col :span="8">
            <el-form-item label="房屋类型" >
              <el-select v-model="form.housetypevalue" placeholder="请选择">
                <el-option v-for="item in form.housetype" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产权类型" >
              <el-select v-model="form.propertyrightstypevalue" placeholder="请选择">
                <el-option v-for="item in form.propertyrightstype" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产权年限" >
              <el-select v-model="form.propertyrightsyearvalue" placeholder="电梯情况">
                <el-option v-for="item in form.propertyrightsyear" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="面积" >
              <el-input v-model="form.square" placeholder="请填写房屋面积"><template slot="append">平方</template></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="售价" >
              <el-input v-model="form.price" placeholder="请填写房屋售价"><template slot="append">万</template></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="24">
            <el-form-item label="图片上传" >
              <div>
                <el-upload action="https://jsonplaceholder.typicode.com/posts/"
                           :auto-upload="false" list-type="picture-card" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :on-change="fun">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="form.dialogVisible">
                  <img width="100%" :src="form.dialogImageUrl" alt="">
                </el-dialog>
              </div>
            </el-form-item>
          </el-col>
        </el-row>


        <el-row :gutter="30">
          <el-col :span="24">
            <el-form-item label="房屋特色" >
              <el-input type="textarea" placeholder="请输入房屋的特色" v-model="form.feature" maxlength="120" show-word-limit></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button type="success"  @click="submit" round>发布</el-button>
          <el-button v-on:click="clearall" round>清空</el-button>
        </el-form-item>



      </el-form>
    </div>
  </div>
</template>

<script>
    export default {
        name: "SaleUpload",
        data() {
          return {
            form: {

              //表题
              title: '',

              //地区
              loaction:'',

              //室的数量
              shi: [{
                value: '1',
                label: '1室'
              }, {
                value: '2',
                label: '2室'
              }, {
                value: '3',
                label: '3室'
              }, {
                value: '4',
                label: '4室'
              }, {
                value: '5',
                label: '5室'
              }, {
                value: '6',
                label: '6室'
              }, {
                value: '7',
                label: '7室'
              }, {
                value: '8',
                label: '8室'
              }],
              shivalue: '',

              //厅的数量
              ting: [{
                value: '1',
                label: '1厅'
              }, {
                value: '2',
                label: '2厅'
              }, {
                value: '3',
                label: '3厅'
              }, {
                value: '4',
                label: '4厅'
              }, {
                value: '5',
                label: '5厅'
              }, {
                value: '6',
                label: '6厅'
              }, {
                value: '7',
                label: '7厅'
              }, {
                value: '8',
                label: '8厅'
              }],
              tingvalue: '',




              //卫的数量
              wei: [{
                value: '1',
                label: '1卫'
              }, {
                value: '2',
                label: '2卫'
              }, {
                value: '3',
                label: '3卫'
              }, {
                value: '4',
                label: '4卫'
              }, {
                value: '5',
                label: '5卫'
              }, {
                value: '6',
                label: '6卫'
              }, {
                value: '7',
                label: '7卫'
              }, {
                value: '8',
                label: '8卫'
              }],
              weivalue: '',


              //房屋的朝向
              direction: [{
                value: '东',
                label: '东'
              }, {
                value: '南',
                label: '南'
              }, {
                value: '西',
                label: '西'
              }, {
                value: '北',
                label: '北'
              }, {
                value: '东西',
                label: '东西'
              }, {
                value: '南北',
                label: '南北'
              }, {
                value: '东南',
                label: '东南'
              }, {
                value: '西南',
                label: '西南'
              }, {
                value: '东北',
                label: '东北'
              }, {
                value: '西北',
                label: '西北'
              }],
              directionvalue: '',

              //房屋所在层数
              layernumber: [{value: '-9', label: '-9'}, {value: '-8', label: '-8'}, {value: '-7', label: '-7'}, {value: '-6', label: '-6'}, {value: '-5', label: '-5'}, {value: '-4', label: '-4'}, {value: '-3', label: '-3'}, {value: '-2', label: '-2'}, {value: '-1', label: '-1'}, {value: '1', label: '1'}, {value: '2', label: '2'}, {value: '3', label: '3'}, {value: '4', label: '4'}, {value: '5', label: '5'}, {value: '6', label: '6'}, {value: '7', label: '7'}, {value: '8', label: '8'}, {value: '9', label: '9'}, {value: '10', label: '10'}, {value: '11', label: '11'}, {value: '12', label: '12'}, {value: '13', label: '13'}, {value: '14', label: '14'}, {value: '15', label: '15'}, {value: '16', label: '16'}, {value: '17', label: '17'}, {value: '18', label: '18'}, {value: '19', label: '19'}, {value: '20', label: '20'}, {value: '21', label: '21'}, {value: '22', label: '22'}, {value: '23', label: '23'}, {value: '24', label: '24'}, {value: '25', label: '25'}, {value: '26', label: '26'}, {value: '27', label: '27'}, {value: '28', label: '28'}, {value: '29', label: '29'}, {value: '30', label: '30'}, {value: '31', label: '31'}, {value: '32', label: '32'}, {value: '33', label: '33'}],
              layernumbervalue: '',

              //房屋总共层数
              layertotal: [{value: '-9', label: '-9'}, {value: '-8', label: '-8'}, {value: '-7', label: '-7'}, {value: '-6', label: '-6'}, {value: '-5', label: '-5'}, {value: '-4', label: '-4'}, {value: '-3', label: '-3'}, {value: '-2', label: '-2'}, {value: '-1', label: '-1'}, {value: '1', label: '1'}, {value: '2', label: '2'}, {value: '3', label: '3'}, {value: '4', label: '4'}, {value: '5', label: '5'}, {value: '6', label: '6'}, {value: '7', label: '7'}, {value: '8', label: '8'}, {value: '9', label: '9'}, {value: '10', label: '10'}, {value: '11', label: '11'}, {value: '12', label: '12'}, {value: '13', label: '13'}, {value: '14', label: '14'}, {value: '15', label: '15'}, {value: '16', label: '16'}, {value: '17', label: '17'}, {value: '18', label: '18'}, {value: '19', label: '19'}, {value: '20', label: '20'}, {value: '21', label: '21'}, {value: '22', label: '22'}, {value: '23', label: '23'}, {value: '24', label: '24'}, {value: '25', label: '25'}, {value: '26', label: '26'}, {value: '27', label: '27'}, {value: '28', label: '28'}, {value: '29', label: '29'}, {value: '30', label: '30'}, {value: '31', label: '31'}, {value: '32', label: '32'}, {value: '33', label: '33'}],
              layertotalvalue: '',



              //装修情况
              decoration: [{
                value: '毛坯',
                label: '毛坯'
              }, {
                value: '简单装修',
                label: '简单装修'
              }, {
                value: '中等装修',
                label: '中等装修'
              }, {
                value: '精装修',
                label: '精装修'
              }, {
                value: '豪华',
                label: '豪华'
              }],
              decorationvalue: '',




              //供暖情况
              heat: [{
                value: '集中供暖',
                label: '集中供暖'
              }, {
                value: '自供暖',
                label: '自供暖'
              }, {
                value: '不供暖',
                label: '不供暖'
              }],
              heatvalue: '',




              //电梯情况
              elevator: [{
                value: '有电梯',
                label: '有电梯'
              }, {
                value: '无电梯',
                label: '无电梯'
              }],
              elevatorvalue: '',




              //房屋类型
              housetype: [{
                value: '普通住宅',
                label: '普通住宅'
              }, {
                value: '别墅',
                label: '别墅'
              }, {
                value: '平房',
                label: '平房'
              }, {
                value: '新里洋房',
                label: '新里洋房'
              }, {
                value: '四合院',
                label: '四合院'
              },{
                value: '排屋',
                label: '排屋'
              }, {
                value: '商住楼',
                label: '商住楼'
              }, {
                value: '其他',
                label: '其他'
              }],
              housetypevalue: '',


              //产权类型
              propertyrightstype: [{
                value: '商品房住宅',
                label: '商品房住宅'
              }, {
                value: '保证型住宅',
                label: '保证型住宅'
              }, {
                value: '公房',
                label: '公房'
              }, {
                value: '商住两用',
                label: '商住两用'
              }, {
                value: '动迁配套房',
                label: '动迁配套房'
              },{
                value: '其他',
                label: '其他'
              }],
              propertyrightstypevalue: '',


              //产权年限
              propertyrightsyear: [{
                value: '70年产权',
                label: '70年产权'
              }, {
                value: '50年产权',
                label: '50年产权'
              }, {
                value: '40年产权',
                label: '40年产权'
              }],
              propertyrightsyearvalue: '',


              //房屋面积
              square:'',

              //售价
              price:'',


              //图片上传
              dialogImageUrl: '',
              dialogVisible: false,
              fileList:'',


              //房屋的特色
              feature:'',

              //房屋在数据库的ID
              maxid:''



            }
          }
        },
      methods: {
        beforeRemove(file, fileList) {
          //return this.$confirm(`确定移除 ${ file.name }？`);
        },
        handleRemove(file, fileList) {
          console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
          this.form.dialogImageUrl = file.url;
          this.form.dialogVisible = true;
        },
        fun(file,fileList){
          console.log(this.form.fileList);
          this.form.fileList = fileList;
        },
        clearall: function(){
          this.form.title = '' ; this.form.loaction = '' ; this.form.shivalue = '' ;
          this.form.tingvalue = '' ; this.form.weivalue = '' ; this.form.directionvalue = '' ; this.form.layernumbervalue = '' ;
          this.form.layertotalvalue = '' ; this.form.decorationvalue = '' ; this.form.heatvalue = '' ; this.form.elevatorvalue = '' ;
          this.form.housetypevalue = '' ; this.form.propertyrightstypevalue = '' ; this.form.propertyrightsyearvalue = '' ;
          this.form.square = '' ; this.form.price = '' ; this.form.feature = ''
        },
        submit:function () {
          var _this = this;
          if(this.form.title === '' || this.form.loaction === '' || this.form.shivalue === '' ||
            this.form.tingvalue === '' || this.form.weivalue === '' || this.form.directionvalue === '' || this.form.layernumbervalue === '' ||
            this.form.layertotalvalue === '' || this.form.decorationvalue === '' || this.form.heatvalue === '' || this.form.elevatorvalue === '' ||
            this.form.housetypevalue === '' || this.form.propertyrightstypevalue === '' || this.form.propertyrightsyearvalue === '' ||
            this.form.square === '' || this.form.price === '' || this.form.feature === ''){
            alert("请正确填写信息！");
            return;
          }

          var formData = new FormData();
          formData.append('username',this.$store.state.username);
          formData.append('title',this.form.title);
          formData.append('loaction',this.form.loaction);
          formData.append('shi',this.form.shivalue);
          formData.append('ting',this.form.tingvalue);
          formData.append('wei',this.form.weivalue);
          formData.append('direction',this.form.directionvalue);
          formData.append('layernumber',this.form.layernumbervalue);
          formData.append('layertotal',this.form.layertotalvalue);
          formData.append('decoration',this.form.decorationvalue);
          formData.append('heat',this.form.heatvalue);
          formData.append('elevator',this.form.elevatorvalue);
          formData.append('housetype',this.form.housetypevalue);
          formData.append('propertyrightstype',this.form.propertyrightstypevalue);
          formData.append('propertyrightsyear',this.form.propertyrightsyearvalue);
          formData.append('square',this.form.square);
          formData.append('price',this.form.price);
          formData.append('feature',this.form.feature);
          for(let item of this.form.fileList){
            formData.append('image',item.raw);
          }
          let config ={
            headers:{"Content-Type":"multipart/form-data"}
          };
          _this.axios.post('/saleupload',formData,config).then(function (response) {
            _this.form.maxid = response.data;
              alert("上传成功");
              //储存房屋的价格日志
              var formData1 = new FormData();
              var time = new Date();
              var timetemp = time.toLocaleString();
              formData1.append('time',timetemp);
              formData1.append('houseid',_this.form.maxid);
              formData1.append('price',_this.form.price);
              _this.axios.post('/storepricelog',formData1,config).then(function (response) {
                
              }).catch(function (error) {
                console.log(error)
              })
          }).catch(function (error) {
            console.log(error);
          })


          // //上传图片
          // var formData = new FormData();
          // console.log(this.form.fileList);
          // formData.append('username',this.$store.state.username);
          // console.log(formData.get("username"));
          // for(let item of this.form.fileList){
          //   formData.append('image',item.raw);
          // }
          // let config ={
          //   headers:{"Content-Type":"multipart/form-data"}
          // };
          // this.axios.post("/saleuploadimag",formData,config).then(function (response) {
          //   console.log(response);
          // }).catch(function (error) {
          //   console.log(error);
          // });
          // return;
          //
          // if(this.form.title === '' || this.form.loaction === '' || this.form.shivalue === '' ||
          //  this.form.tingvalue === '' || this.form.weivalue === '' || this.form.directionvalue === '' || this.form.layernumbervalue === '' ||
          //  this.form.layertotalvalue === '' || this.form.decorationvalue === '' || this.form.heatvalue === '' || this.form.elevatorvalue === '' ||
          //  this.form.housetypevalue === '' || this.form.propertyrightstypevalue === '' || this.form.propertyrightsyearvalue === '' ||
          //  this.form.square === '' || this.form.price === '' || this.form.feature === ''){
          //   alert("请正确填写信息！");
          //   return;
          // }
          // this.axios.post("/sale")
          // this.axios.post("/saleupload",{username:this.$store.state.username,title:this.form.title,loaction:this.form.loaction,shi:this.form.shivalue,
          // ting:this.form.tingvalue,wei:this.form.weivalue,direction:this.form.directionvalue,layernumber:this.form.layernumbervalue,
          //   layertotal:this.form.layertotalvalue,decoration:this.form.decorationvalue,heat:this.form.heatvalue,elevator:this.form.elevatorvalue,
          //   housetype:this.form.housetypevalue,propertyrightstype:this.form.propertyrightstypevalue,propertyrightsyear:this.form.propertyrightsyearvalue,
          //   square:this.form.square,price:this.form.price,feature:this.form.feature}).then(function (response) {
          //     if(response.data === "ok"){
          //       alert("房屋信息上传成功！");
          //     }
          // }).catch(function (error) {
          //   console.log(error);
          // })
        }
      }
    }
</script>

<style scoped>
  #upload {
    width: 700px;
    margin: 0 auto;
  }
</style>
