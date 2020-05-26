package com.house.back.controller;

import com.house.back.pojo.Authority;
import com.house.back.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AuthorityController {
    @Autowired
    @Resource
    AuthorityService authorityService;

    @CrossOrigin
    @RequestMapping("api/findauthoritybyusername")
    @ResponseBody
    public List<Authority> findauthoritybyusername(@RequestParam("username") String username){
        List<Authority> list = authorityService.findAuthorityByUserName(username);
        return list;
    }




    //返回所以的权限控制
    @CrossOrigin
    @RequestMapping("api/findallauthority")
    @ResponseBody
    public List<Authority> findallauthority(){
        List<Authority> list = authorityService.findallauthority();
        return list;
    }


    //根据用户名更新相应的权限信息
    @CrossOrigin
    @RequestMapping("api/updateauthoritybyusername")
    @ResponseBody
    public void updateauthoritybyusername(@RequestBody Authority authority){
        String username = authority.getUsername();
        String houseupload = authority.getHouseupload();
        String uploadmanage = authority.getUploadmanage();
        String collectionrecord = authority.getCollectionrecord();
        String browserrecord = authority.getBrowserrecord();
        String infomodify = authority.getInfomodify();
        String mymessage = authority.getMymessage();
        authorityService.updateauthoritybyusername(username,houseupload,uploadmanage,collectionrecord,browserrecord,infomodify,mymessage);
    }


    //根据用户名删除权限实体
    @CrossOrigin
    @RequestMapping("api/deleteauthoritybyusername")
    @ResponseBody
    public void deleteauthoritybyusername(@RequestParam("username") String username){
        authorityService.deleteauthoritybyusername(username);
    }




}
