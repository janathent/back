package com.house.back.dao;

import com.house.back.pojo.BrowseAllNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrowseAllNumberDao extends JpaRepository<BrowseAllNumber,Integer> {



    //当是新的一天时，就实现创建新的存储量
    @Query(value = "insert into browseallnumber (time, number) values ( ?1 , ?2)",nativeQuery = true)
    @Modifying
    public void addbrowswallnumber(String time,String number);


    //当不是新的一天时，就实现浏览量的更新
    @Query(value = "update browseallnumber set  browseallnumber.number = ?1 where browseallnumber.time = ?2",nativeQuery = true)
    @Modifying
    public void browseallnumberbytime(String number,String time);



    //得到最后一条数据库记录
    @Query(value = "select * from browseallnumber where id=(select max(id) from browseallnumber)",nativeQuery = true)
    public BrowseAllNumber returnlastbrowseallnumber();


    //得到所有的数据库记录
    @Query(value = "select * from browseallnumber",nativeQuery = true)
    public List<BrowseAllNumber> returnall();

}
