package com.house.back.service;

import com.house.back.dao.SaleDao;
import com.house.back.pojo.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaleService {
    @Autowired
    SaleDao saleDao;


    //添加一个房屋
    public void addSale(Sale sale){
        saleDao.save(sale);
    }


    //根据用户名查找所有的房屋详细
    public List<Sale> findSaleByName(String username){
        List<Sale> list = saleDao.findSaleByName(username);
        return list;
    }


    //根据ID查找所有的房屋详细
    public List<Sale> findSaleById(Integer id){
        List<Sale> list = saleDao.findSaleById(id);
        return list;
    }


    public List<Sale> findall(){
       List<Sale> list = saleDao.findall();
       return list;
    }


    //获得当前数据的最大ID
    public Integer findMaxId(){
        Integer max = saleDao.findMaxId();
        return max;
    }

    public void deleteSaleById(int id){
        saleDao.deleteSaleById(id);
    }


    //根据ID来修改房屋的属性
    public void updateSaleById(String decoration,String direction,String elevator,String feature,String heat
            ,String housetype,String layernumber,String layertotal,String loaction,String price,String propertyrightstype
            ,String propertyrightsyear,String shi,String square,String ting,String title,String username,String wei
            ,Integer id){
        saleDao.updateSaleById(decoration,direction,elevator,feature,heat,housetype,layernumber,layertotal,loaction,
                price,propertyrightstype,propertyrightsyear,shi,square,ting,title,username,wei,id);
    }


    //更新房屋属性数据
    public void updatesalepicpathbyid(String picpath,Integer id){
        saleDao.updatesalepicpathbyid(picpath,id);
    }

    public void updatesalepicnumpathbyid(String picnum,Integer id){
        saleDao.updatesalepicnumpathbyid(picnum,id);
    }


    public List<Sale> searchshowmain(String searchitem){
        List<Sale> list = saleDao.searchshowmain(searchitem);
        return list;
    }



}
