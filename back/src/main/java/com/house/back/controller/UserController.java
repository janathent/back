package com.house.back.controller;

import com.house.back.Response.Response;
import com.house.back.dao.UserDao;
import com.house.back.pojo.Authority;
import com.house.back.pojo.Sale;
import com.house.back.pojo.User;
import com.house.back.service.AuthorityService;
import com.house.back.service.SaleService;
import com.house.back.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;




@Controller
public class UserController {
    @Autowired
    UserService userService;  //创建service的对象

    @Autowired  //保存注册权限
    AuthorityService authorityService;



    @CrossOrigin
    @PostMapping("/api/register")
    @ResponseBody
    public String register(@RequestBody User user){
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        String password = user.getPassword();

        boolean isExist = userService.isExist(username);
        if(isExist){
            return "用户名已经存在";
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";
        String pwdAfterHash = new SimpleHash(algorithm,password,salt,times).toString();
        user.setSalt(salt);
        user.setPassword(pwdAfterHash);

        String phone = user.getPhone();
        user.setPhone(phone);

        String gender = user.getGender();
        user.setGender(gender);

        String birth = user.getBirth();
        user.setBirth(birth);

        Authority authority = new Authority();
        authority.setUsername(username);
        authority.setHouseupload("1");
        authority.setUploadmanage("1");
        authority.setCollectionrecord("1");
        authority.setBrowserrecord("1");
        authority.setMymessage("1");
        authority.setInfomodify("1");
        authorityService.addAuhority(authority);

        userService.addUser(user);
        return "成功";
    }

    @CrossOrigin
    @PostMapping("api/login")
    @ResponseBody
    public Response login(@RequestBody User user){
        String username = user.getUsername();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,user.getPassword());
        try{
            subject.login(usernamePasswordToken);
            return new Response(200,"success",usernamePasswordToken);
        }catch (AuthenticationException e){
            return new Response(500,"failure",null);
        }
    }




    //根据用户名找到用户
    @CrossOrigin
    @PostMapping("api/finduserbyname")
    @ResponseBody
    public User finduserbyname(@RequestParam("username") String username){
        User user =  userService.findUserByUsername(username);
        return user;
    }

    //根据用户名更新
    @CrossOrigin
    @PostMapping("api/updateuserbyname")
    @ResponseBody
    public String updateuserbyname(@RequestParam("username") String username,@RequestParam("phone") String phone,@RequestParam("gender") String gender,@RequestParam("birth") String birth){
        userService.updateUserByUsername(phone,gender,birth,username);
        return "ok";
    }



    //根据用户名操作电话
    @CrossOrigin
    @PostMapping("api/findPhonebyusername")
    @ResponseBody
    public String findPhoneByUserName(@RequestParam("username") String username){
        String phone = userService.findPhoneByUserName(username);
        return phone;
    }


    //根据ID删除用户
    @CrossOrigin
    @PostMapping("api/deleteuserbyid")
    @ResponseBody
    public String deleteuserbyid(@RequestParam("id") Integer id){
        userService.deleteUserById(id);
        return "ok";
    }

//
//    @CrossOrigin
//    @RequestMapping("/api/uerinfomodify") //用户信息修改
//    @ResponseBody
//    public String uerinfomodify(@ResponseBody User user){
//
//    }
}
