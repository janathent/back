package com.house.back.dao;

import com.house.back.pojo.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityDao extends JpaRepository<Authority,Integer> {


    //根据用户名查找用户权限实体
    @Query(value = "select * from authority where username = ?1" ,nativeQuery = true)
    public List<Authority> findAuthorityByUserName(String username);

    //返回全部的权限设计表格
    @Query(value = "select * from authority" ,nativeQuery = true)
    public List<Authority> findallauthority();


    //根据用户名修改权限实体
    @Query(value = "update authority set authority.houseupload=?2 , authority.uploadmanage=?3 , authority.collectionrecord=?4 , authority.browserrecord=?5 , authority.infomodify=?6 , authority.mymessage=?7 where authority.username=?1 ", nativeQuery = true)
    @Modifying
    public void updateauthoritybyusername(String username,String houseupload,String uploadmanage,String collectionrecord,
                                          String browserrecord,String infomodify,String mymessage);


    //根据用户名删除权限实体
    @Query(value = "delete from authority where username=?1 ", nativeQuery = true)
    @Modifying
    public void deleteauthoritybyusername(String username);

}
