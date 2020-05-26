package com.house.back.controller;


import com.house.back.service.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class BrowseController {
    @Autowired
    @Resource
    BrowseService browseService;



    @CrossOrigin
    @RequestMapping("api/storebrowse")
    @ResponseBody
    public void storebrowse(@RequestParam("username") String username,@RequestParam("browseid") String browseid,@RequestParam("time") String time){
        browseService.storechathistory(username,browseid,time);
    }

}
