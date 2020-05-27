package com.house.back;

import com.alibaba.fastjson.JSON;
import com.house.back.pojo.Sale;
import com.house.back.service.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Stack;

@SpringBootTest
class BackApplicationTests {

    @Autowired
    @Resource
    SaleService saleService;


    @Test
    void contextLoads() {
        Sale sale = saleService.findSaleById(42).get(0);
        String salestring = JSON.toJSONString(sale);
        System.out.println(salestring);



    }

}
