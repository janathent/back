<template>
  <div>
    <ve-line :data="chartData" :settings="chartSettings"></ve-line>
  </div>
</template>

<script>
    export default {
        name: "ManageUserAccessChart",
      data () {

        return {
          chartSettings :{
            metrics: ['访问用户']
          },
          chartData: {
            columns: ['日期', '访问用户'],
            rows: [
              // { '日期': '2018-05-22',  '访问用户': 19810 },
              // { '日期': '2018-05-23',  '访问用户': 4398 },
              // { '日期': '2018-05-24',  '访问用户': 52910 }
            ]
          },
          //存储数据
          list:'',
        }
      },
      created() {
        var _this = this;
        _this.axios.post('/returnallbrowseallnumber').then(function (response) {
          _this.list = response.data;
          for (var i = 0 ; i < response.data.length ; ++i){
            var obj = {'日期':response.data[i].time, '访问用户': response.data[i].number};
            _this.chartData.rows.push(obj)
          }
          console.log(response)
        }).catch(function (error) {
          console.log(error)
        })
      }
    }
</script>

<style scoped>

</style>
