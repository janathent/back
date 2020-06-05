package com.house.back.controller;


import com.house.back.pojo.BrowseAllNumber;
import com.house.back.service.BrowseAllNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BrowseAllNumberController {

    @Autowired
    @Resource
    BrowseAllNumberService browseAllNumberService;


    @CrossOrigin
    @RequestMapping("api/storebrowseallnumber")
    @ResponseBody
    public void storebrowseallnumber(@RequestParam("time") String time){
        String lasttime = browseAllNumberService.returnlastbrowseallnumber().getTime();
        //时间相同
        if(time.equals(lasttime)){
            Integer currentnumber = Integer.parseInt(browseAllNumberService.returnlastbrowseallnumber().getNumber());
            currentnumber += 1;
            String newnumber = Integer.toString(currentnumber);
            //更新数据
            browseAllNumberService.browseallnumberbytime(newnumber,time);
        }else{
            //第二天
            browseAllNumberService.addbrowswallnumber(time,"1");
        }
    }


    //返回所有的数据图
    @CrossOrigin
    @RequestMapping("api/returnallbrowseallnumber")
    @ResponseBody
    public List<BrowseAllNumber> returnallbrowseallnumber(){
        List<BrowseAllNumber> list = browseAllNumberService.returnall();
        return list;
    }


}
