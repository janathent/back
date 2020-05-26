package com.house.back.service;

import com.house.back.dao.CollectionDao;
import com.house.back.pojo.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollectionService {
    @Autowired
    CollectionDao collectionDao;

    //取消收藏
    public void cancelcollection(String username,String collectionid){
        collectionDao.cancelcollection(username,collectionid);
    }


    //新增收藏
    public void addcollection(String username,String collectionid){
        collectionDao.addcollection(username,collectionid);
    }

    //查询收藏
    public List<Collection> findcollection(String username, String collectionid){
        List<Collection> list = collectionDao.findcollection(username,collectionid);
        return list;
    }

    //根据用户名找到收藏的所有ID
    public List<Object> findcollectionbyname(String username){
        List<Object> list = collectionDao.findcollectionbyname(username);
        return list;
    }
}
