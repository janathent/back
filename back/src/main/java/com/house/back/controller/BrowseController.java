package com.house.back.controller;


import com.alibaba.fastjson.JSON;
import com.house.back.pojo.Browse;
import com.house.back.pojo.Sale;
import com.house.back.service.BrowseService;
import com.house.back.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class BrowseController {
    @Autowired
    @Resource
    BrowseService browseService;

    @Autowired
    @Resource
    SaleService saleService;

    @CrossOrigin
    @RequestMapping("api/storebrowse")
    @ResponseBody
    public void storebrowse(@RequestParam("username") String username,@RequestParam("browseid") String browseid,@RequestParam("time") String time){
        browseService.storechathistory(username,browseid,time);
    }

    @CrossOrigin
    @RequestMapping("api/findallbrowsebyusername")
    @ResponseBody
    public List<Map<String,Object>> findallbrowsebyusername(@RequestParam("username") String username){
        List<Browse> browseList = browseService.findallbrowsebyusername(username);
        int browselength = browseList.size();
        List<Map<String,Object>> saleList = new ArrayList<>();
        Stack<Browse> stack = new Stack();
        //若是存在浏览的列表
        if(browselength != 0) {
            for (int i = 0; i < browseList.size(); ++i) {
                //实现浏览的时间逆置
                stack.push(browseList.get(i));
            }
            browseList.clear();
            for(int i = 0 ; i < browselength ; ++i){
                browseList.add(stack.pop());
            }
            for(int i = 0 ; i < browseList.size() - 1 ;++i){
                for (int j = i + 1 ; j < browseList.size() ;++j){
                    if(browseList.get(i).getBrowseid().equals(browseList.get(j).getBrowseid())){
                        browseList.remove(j);
                        j--;
                    }
                }
            }
            for (int i = 0 ; i < browseList.size() ;++i){
                int index = Integer.parseInt(browseList.get(i).getBrowseid());
                if(saleService.findSaleById(index).size() != 0){
                    Sale saletemp = saleService.findSaleById(index).get(0);
                    String salestring = JSON.toJSONString(saletemp);
                    Object timetemp = browseList.get(i).getTime();
                    Map<String,Object> map = new HashMap<>();
                    map.put(salestring,timetemp);
                    saleList.add(map);
                }
            }
        }else{
            return null;
        }
        return saleList;
    }


    //根据用户名删除浏览记录
    @CrossOrigin
    @RequestMapping("api/deletebrowsebyusername")
    @ResponseBody
    public void deletebrowsebyusername(@RequestParam("username") String username){
        browseService.deletebrowsebyusername(username);
    }



}
