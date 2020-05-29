package com.house.back;

import com.alibaba.fastjson.JSON;
import com.house.back.pojo.Sale;
import com.house.back.service.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

@SpringBootTest
class BackApplicationTests {



    @Test
    void contextLoads() {
        String path = "C:\\Users\\黄和龙\\Desktop\\test\\1.jpg";
        File file = new File(path);
        if(file.exists()){
            file.delete();
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

    }

}
