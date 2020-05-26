import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state:{
    // currentUser:window.localStorage.getItem('user') === null?'':JSON.parse(window.localStorage.getItem('user'))
    currentUser:window.localStorage.getItem('user') === null?'':JSON.parse(window.localStorage.getItem('user')),
    username:'请登录',   //首页用户显示登录成功的用户名
    bigtitle:''   //显示在个人中心上面的操作标题
  },
  mutations:{
    login(state,user){
      state.currentUser = user;
      window.localStorage.setItem("user",JSON.stringify(user));
      console.log(user);
    },
    username(state,username){
      state.username = username;
    },
    changetitle(state,title){
      state.bigtitle = title;
    }
  }
})

export default store
