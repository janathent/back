package com.house.back.service;


import com.house.back.dao.BrowseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrowseService {
    @Autowired
    BrowseDao browseDao;


    public void storechathistory(String username,String browseid,String time){
        browseDao.storechathistory(username,browseid,time);
    }





}
