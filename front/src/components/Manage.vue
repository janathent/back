<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1','2','3']" router>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-s-custom"></i>用户管理</template>
            <el-menu-item-group>
              <el-menu-item index="/manageuser" @click="changetitle1">用户信息</el-menu-item>
              <el-menu-item index="/manageuserauthority" @click="changetitle2">用户权限</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title"><i class="el-icon-s-home"></i>房屋管理</template>
            <el-menu-item-group>
              <el-menu-item index="/manageshowsale"  @click="changetitle3">房屋修改</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title"><i class="el-icon-message"></i>系统数据</template>
            <el-menu-item-group>
              <el-menu-item index="/manageuseraccesschart">访问流量</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
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
        name: "Manage",
      data() {
        return {

        }
      },
      methods:{
          changetitle1:function () {
            this.$store.commit('changetitle',"用户信息修改");
          },
        changetitle2:function () {
          this.$store.commit('changetitle',"用户权限管理");
        },
        changetitle3:function () {
            this.$store.commit('changetitle','用户房屋修改')
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
