package com.house.back.service;


import com.house.back.dao.BrowseAllNumberDao;
import com.house.back.pojo.BrowseAllNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrowseAllNumberService {

    @Autowired
    BrowseAllNumberDao browseAllNumberDao;

    //当是新的一天时，就实现创建新的存储量
    public void addbrowswallnumber(String time,String number){
        browseAllNumberDao.addbrowswallnumber(time,number);
    }


    //当不是新的一天时，就实现浏览量的更新
    public void browseallnumberbytime(String number,String time){
        browseAllNumberDao.browseallnumberbytime(number,time);
    }


    //得到最后一条数据库记录
    public BrowseAllNumber returnlastbrowseallnumber(){
        BrowseAllNumber browseAllNumber = browseAllNumberDao.returnlastbrowseallnumber();
        return browseAllNumber;
    }

    //得到所有的数据库记录
    public List<BrowseAllNumber> returnall(){
        List<BrowseAllNumber> list = browseAllNumberDao.returnall();
        return list;
    }

}
