<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1']" router>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-s-custom"></i>用户管理</template>
            <el-menu-item-group>
              <el-menu-item index="/manageuser" @click="changetitle1">用户信息</el-menu-item>
              <el-menu-item index="/manageuserauthority" @click="changetitle2">用户权限</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title"><i class="el-icon-message"></i>房屋管理</template>
            <el-menu-item-group>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title"><i class="el-icon-message"></i>导航三</template>
            <el-menu-item-group>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px;background-color: #545c64;">
          <strong style="float: left;margin-left: 40%;font-size: 26px;color: #fff">{{$store.state.bigtitle}}</strong>
          <!--        <el-dropdown>-->
          <!--          <i class="el-icon-setting" style="margin-right: 15px"></i>-->
          <!--          <el-dropdown-menu slot="dropdown">-->
          <!--            <el-dropdown-item>查看</el-dropdown-item>-->
          <!--            <el-dropdown-item>新增</el-dropdown-item>-->
          <!--            <el-dropdown-item>删除</el-dropdown-item>-->
          <!--          </el-dropdown-menu>-->
          <!--        </el-dropdown>-->
          <div style="float: right;height: 50px;"> <span style="font-size: 12px;color: #fff">{{$store.state.username}}</span></div>
          <div style="float: right;height: 50px;margin-right: 8px;margin-top: 10px">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </div>


        </el-header>

        <el-main>
          <!--        <el-table :data="tableData">-->
          <!--          <el-table-column prop="date" label="日期" width="140">-->
          <!--          </el-table-column>-->
          <!--          <el-table-column prop="name" label="姓名" width="120">-->
          <!--          </el-table-column>-->
          <!--          <el-table-column prop="address" label="地址">-->
          <!--          </el-table-column>-->
          <!--        </el-table>-->
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
