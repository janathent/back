package com.house.back.dao;

import com.house.back.pojo.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionDao extends JpaRepository<Collection,Integer> {

    //实现取消收藏
    @Query(value = "delete from collection where username=?1 and collectionid=?2", nativeQuery = true)
    @Modifying
    public void cancelcollection(String username,String collectionid);



    //实现新增收藏
    @Query(value = "insert into collection (username, collectionid) values ( ?1 , ?2)",nativeQuery = true)
    @Modifying
    public void addcollection(String username,String collectionid);


    //找到是的收藏
    @Query(value = "select * from collection where username=?1 and collectionid=?2", nativeQuery = true)
    public List<Collection> findcollection(String username,String collectionid);

    //根据用户名找到所有的收藏
    @Query(value = "select collectionid from collection where username=?1", nativeQuery = true)
    public List<Object> findcollectionbyname(String username);
}


