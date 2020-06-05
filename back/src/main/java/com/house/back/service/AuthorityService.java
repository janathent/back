package com.house.back.service;

import com.house.back.dao.AuthorityDao;
import com.house.back.pojo.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AuthorityService {

    @Autowired
    AuthorityDao authorityDao;


    //根据用户名查找实体对象
    public List<Authority> findAuthorityByUserName(String username){
        List<Authority> list = authorityDao.findAuthorityByUserName(username);
        return list;
    }


    //保存权限对象
    public void addAuhority(Authority authority){
        authorityDao.save(authority);
    }

    //返回全部的权限设计表格
    public List<Authority> findallauthority(){
        List<Authority> list = authorityDao.findallauthority();
        return list;
    }

    //根据用户名修改权限实体
    public void updateauthoritybyusername(String username,String houseupload,String uploadmanage,String collectionrecord,
                                          String browserrecord,String infomodify,String mymessage){
        authorityDao.updateauthoritybyusername(username,houseupload,uploadmanage,collectionrecord,browserrecord,infomodify,mymessage);
    }

    //根据用户名删除权限实体
    public void deleteauthoritybyusername(String username){
        authorityDao.deleteauthoritybyusername(username);
    }


}
