package com.house.back.dao;

import com.house.back.pojo.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleDao extends JpaRepository<Sale,Integer> {


    @Query(value = "SELECT * FROM sale", nativeQuery = true)
    public List<Sale> findall();


    //根据用户名获取有几个用户房屋
    @Query(value = "select * from sale where username=?1 ", nativeQuery = true)
    public List<Sale> findSaleByName(String username);


    //根据ID号获取有几个用户房屋
    @Query(value = "select * from sale where id=?1 ", nativeQuery = true)
    public List<Sale> findSaleById(Integer id);


    //获得当前数据库的最大ID
    @Query(value = "select max(id) from sale", nativeQuery = true)
    public Integer findMaxId();


    //根据id删除房屋信息
    @Query(value = "delete from sale where id=?1 ", nativeQuery = true)
    @Modifying
    public void deleteSaleById(int id);





    //根据ID来修改房屋的属性
    @Query(value = "update sale set sale.decoration=?1,sale.direction=?2,sale.elevator=?3," +
            " sale.feature=?4,sale.heat=?5,sale.housetype=?6,sale.layernumber=?7,sale.layertotal=?8," +
            " sale.loaction=?9,sale.price=?10,sale.propertyrightstype=?11,sale.propertyrightsyear=?12," +
            " sale.shi=?13,sale.square=?14,sale.ting=?15,sale.title=?16,sale.username=?17,sale.wei=?18" +
            " where sale.id=?19 ", nativeQuery = true)
    @Modifying
    public void updateSaleById(String decoration,String direction,String elevator,String feature,String heat
    ,String housetype,String layernumber,String layertotal,String loaction,String price,String propertyrightstype
    ,String propertyrightsyear,String shi,String square,String ting,String title,String username,String wei
    ,Integer id);


}
