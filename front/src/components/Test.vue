<template>
  <div>
    <el-upload
      action="https://jsonplaceholder.typicode.com/posts/" list-type="picture-card" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :auto-upload="false" :on-change="addfilelist">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return{
        dialogImageUrl: '',
        dialogVisible: false
      }
    },
    created() {

    }
    ,
    methods:{
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        console.log(file)
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      addfilelist:function (file,fileList) {

      }
    }

  }
</script>
<style scoped>

</style>
