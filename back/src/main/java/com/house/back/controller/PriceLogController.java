package com.house.back.controller;

import com.house.back.service.PriceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PriceLogController {

    @Autowired
    PriceLogService priceLogService;

    //存储一条房屋价格记录
    @CrossOrigin
    @RequestMapping("api/storepricelog")
    @ResponseBody
    public void storepricelog(@RequestParam("houseid") String houseid,@RequestParam("price") String price,@RequestParam("time") String time){
        System.out.println(houseid);
        System.out.println(price);
        System.out.println(time);
        priceLogService.storepricelog(houseid,price,time);
    }
}
