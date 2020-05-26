package com.house.back.service;

import com.house.back.dao.UserDao;
import com.house.back.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class UserService {

    @Autowired

    UserDao userDao;  //把dao层给引进来，创建dao的对象

    public User getUserByUsername(String username){
        return userDao.findByUsername(username);
    }

    public boolean isExist(String username){
        User user = getUserByUsername(username);
        return user!=null;
    }

    public void addUser(User user){
        userDao.save(user);
    }


    //test
    public void deleteUserById(Integer id){
        userDao.deleteUserById(id);
    }


    public User findUserById(Integer id){
        User user =  userDao.findUserById(id);
        return user;
    }

    public void updateUserphone(String phone,int id){
        userDao.updateUserphone(phone,id);
    }


    public List<User> findUserByGender(String gender){
       List<User> users = userDao.findUserByGender(gender);
       return users;
    }

   //根据用户名找到用户返回信息
    public User findUserByUsername(String username){
        User user = userDao.findUserByUsername(username);
        return user;
    }

    //根据用户名修改全表
    public void updateUserByUsername(String phone,String gender,String birth,String username){
        userDao.updateUserByUsername(phone,gender,birth,username);
    }


    //根用户名找到电话
    public String findPhoneByUserName(String username){
        String phone = userDao.findPhoneByUserName(username);
        return phone;
    }



    //返回全部用户
    @Query(value = "select * from user", nativeQuery = true)
    public List<User> managefindalluser(){
        List<User> list = userDao.managefindalluser();
        return list;
    }

}
