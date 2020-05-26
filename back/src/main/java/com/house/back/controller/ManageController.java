package com.house.back.controller;


import com.house.back.pojo.User;
import com.house.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ManageController {


    @Autowired
    UserService userService;  //创建service的对象


    @CrossOrigin
    @RequestMapping("api/managefindalluser")
    @ResponseBody
    public List<User> managefindalluser(){
        List<User> list = userService.managefindalluser();
        return list;
    }

}
