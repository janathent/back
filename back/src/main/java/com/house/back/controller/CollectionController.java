package com.house.back.controller;


import com.house.back.pojo.Collection;
import com.house.back.pojo.Sale;
import com.house.back.service.CollectionService;
import com.house.back.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CollectionController {
    @Autowired
    @Resource
    CollectionService collectionService;
    @Autowired
    @Resource
    SaleService saleService;



    //实现取消收藏
    @CrossOrigin
    @RequestMapping("api/cancelcollection")
    @ResponseBody
    public String cancelcollection(@RequestParam("username") String username,@RequestParam("collectionid") String collectionid){
        collectionService.cancelcollection(username,collectionid);
        return "ok";
    }

    //实现新增收藏
    @CrossOrigin
    @RequestMapping("api/addcollection")
    @ResponseBody
    public String addcollection(@RequestParam("username") String username,@RequestParam("collectionid") String collectionid){
        collectionService.addcollection(username,collectionid);
        return "ok";
    }

    //查询收藏
    @CrossOrigin
    @RequestMapping("api/findcollection")
    @ResponseBody
    public boolean findcollection(@RequestParam("username") String username,@RequestParam("collectionid") String collectionid){
        List<Collection> list = collectionService.findcollection(username,collectionid);
        if(list.size() == 1){
            return true;
        }else{
            return false;
        }
    }


    //根据用户名返回显示所有的收藏实体
    @CrossOrigin
    @RequestMapping("api/returnallcollection")
    @ResponseBody
    public List<Sale> returnallcollection(@RequestParam("username") String username){
        List<Object> list = collectionService.findcollectionbyname(username);
        List<Sale> saleList = new ArrayList<Sale>();
        for (int i = 0 ; i < list.size() ;++i ){
            Integer id = Integer.parseInt(String.valueOf(list.get(i)));
            saleList.add(saleService.findSaleById(id).get(0));
        }
        return saleList;
    }


}
