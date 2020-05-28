import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Register from "../components/Register";
import Login from "../components/Login";
import ShowMain from "../components/ShowMain";
import Test from "../components/Test";
import MyHome from "../components/MyHome";
import SaleUpload from "../components/SaleUpload";
import SaleManage from "../components/SaleManage";
import SaleModify from "../components/SaleModify";
import SaleDetailer from "../components/SaleDetailer";
import PersonalInfoModify from "../components/PersonalInfoModify";
import MyMessage from "../components/MyMessage";
import MyCollection from "../components/MyCollection";
import Manage from "../components/Manage";
import ManageUser from "../components/ManageUser";
import ManageModifyUser from "../components/ManageModifyUser";
import ManageUserAuthority from "../components/ManageUserAuthority";
import MyBrowse from "../components/MyBrowse";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      children: [
        {path:"/login" ,component: Login},
        {path: "/register",component: Register},
        {path: "/showmain",component: ShowMain},
        {path: "/saledetailer",component: SaleDetailer},
        {path: "/manage",component: Manage, children: [{path: "/manageuser" ,component: ManageUser},{path: "/managemodifyuser" ,component: ManageModifyUser},{path: "/manageuserauthority" ,component: ManageUserAuthority}]},
        {path: "/myhome",component: MyHome, children: [{path: "/saleupload",component: SaleUpload},{path: "/salemanage",component: SaleManage},{path: "/salemodify",component: SaleModify},{path: "/personalinfomodify",component: PersonalInfoModify},{path: "/mymessage",component: MyMessage},{path: "/mycollection",component: MyCollection},{path: "/mybrowse",component: MyBrowse}]}]},
    // {
    //   path: '/register',
    //   name: 'Register',
    //   component: Register
    // },
    // {
    //   path: '/login',
    //   name: 'Login',
    //   component: Login
    // },
    // {
    //   path: '/showmain',
    //   name :'ShowMain',
    //   component: ShowMain,
    // },
    // {
    //   path: '/saleupload',
    //   name: 'SaleUpload',
    //   component: SaleUpload
    // },
    // {
    //   path: '/salemodify',
    //   name: 'SaleModify',
    //   component: SaleModify
    // },
    // {
    //   path: '/saledetailer',
    //   name: 'SaleDetailer',
    //   component: SaleDetailer
    // },
    // {
    //   path: '/personalinfomodify',  //修改个人信息
    //   name: 'PersonalInfoModify',
    //   component: PersonalInfoModify
    // },
    // {
    //   path: '/mymessage',
    //   name: 'mymessage',
    //   component:MyMessage
    // },
    {
      path: '/test',
      name: 'test',
      component:Test
    },
    // {
    //   path: '/myhome',
    //   name: 'MyHome',
    //   component: MyHome,
    //   children: [
    //     {path: "/saleupload",component: SaleUpload},
    //     {path: "/salemanage",component: SaleManage},
    //     {path: "/salemodify",component: SaleModify},
    //     {path: "/personalinfomodify",component: PersonalInfoModify},
    //     {path: '/mymessage', component:MyMessage},
    //     {path: '/collection', component:MyCollection}
    //   ]
    // }
  ]
})
