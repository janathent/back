package com.house.back.service;


import com.house.back.dao.PriceLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PriceLogService {

    @Autowired
    PriceLogDao priceLogDao;


    //储存一条房屋价格日志信息
    public void storepricelog(String houseid,String price,String time){
        priceLogDao.storepricelog(houseid,price,time);
    }

}
