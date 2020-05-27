package com.house.back.dao;

import com.house.back.pojo.Browse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrowseDao extends JpaRepository<Browse,Integer> {


    //插入一条浏览记录
    @Query(value = "insert into browse (username, browseid, time) values ( ?1 , ?2 , ?3 )",nativeQuery = true)
    @Modifying
    public void storechathistory(String username,String browseid,String time);

    //根据用户名返回浏览记录
    @Query(value = "select * from browse where username=?1", nativeQuery = true)
    public List<Browse> findallbrowsebyusername(String username);


    //根据用户名删除浏览记录
    @Query(value = "delete from browse where username = ?1", nativeQuery = true)
    @Modifying
    public void deletebrowsebyusername(String username);

}
