package com.house.back.dao;

import com.house.back.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {  //类是User类 组件的类型是整形


    //写一些基础的增删改查功能
    User findByUsername(String username);

    //test
    //根据id删除User
    @Query(value = "delete from user where id=?1 ", nativeQuery = true)
    @Modifying
    public void deleteUserById(int id);

    //根据id获取数据源
    @Query(value = "select * from user where id=?1 ", nativeQuery = true)
    public User findUserById(int id);

    //修改phone
    @Query(value = "update user set user.phone=?1 where user.id=?2 ", nativeQuery = true)
    @Modifying
    public void updateUserphone(String phone,int id);

    //根据性别获取数据源
    @Query(value = "select * from user where gender=?1 ", nativeQuery = true)
    public List<User> findUserByGender(String gender);

    //根用户名找到用户
    @Query(value = "select * from user where username=?1 ", nativeQuery = true)
    public User findUserByUsername(String username);


    //根据用户名修改全表
    @Query(value = "update user set user.phone=?1 , user.gender=?2 , user.birth=?3 where user.username=?4 ", nativeQuery = true)
    @Modifying
    public void updateUserByUsername(String phone,String gender,String birth,String username);


    //根用户名找到电话
    @Query(value = "select phone from user where username=?1 ", nativeQuery = true)
    public String findPhoneByUserName(String username);



    //返回全部用户
    @Query(value = "select * from user", nativeQuery = true)
    public List<User> managefindalluser();

}
