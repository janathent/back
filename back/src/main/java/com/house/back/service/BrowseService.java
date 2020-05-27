package com.house.back.service;


import com.house.back.dao.BrowseDao;
import com.house.back.pojo.Browse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrowseService {
    @Autowired
    BrowseDao browseDao;


    //存储浏览记录
    public void storechathistory(String username,String browseid,String time){
        browseDao.storechathistory(username,browseid,time);
    }



    //根据用户名返回浏览记录
    public List<Browse> findallbrowsebyusername(String username){
        List<Browse> list = browseDao.findallbrowsebyusername(username);
        return list;
    }

    //根据用户名删除浏览记录
    public void deletebrowsebyusername(String username){
        browseDao.deletebrowsebyusername(username);
    }



}
