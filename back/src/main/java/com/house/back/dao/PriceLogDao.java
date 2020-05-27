package com.house.back.dao;

import com.house.back.pojo.PriceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PriceLogDao extends JpaRepository<PriceLog,Integer> {

    //储存一条房屋价格日志

    @Query(value = "insert into pricelog (houseid, price, time) values ( ?1 , ?2 , ?3 )",nativeQuery = true)
    @Modifying
    public void storepricelog(String houseid,String price,String time);
}
