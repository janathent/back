package com.house.back.dao;

import com.house.back.pojo.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatHistoryDao extends JpaRepository<ChatHistory,Integer> {


    //查询聊天记录
    @Query(value = "select * from chathistory where (messagefrom=?1 and messageto=?2) or (messagefrom=?2 and messageto=?1)", nativeQuery = true)
    public List<ChatHistory> findhistory(String messagefrom,String messageto);

    //查询是谁给我发了信息
    @Query(value = "select * from chathistory where messageto=?1 or messagefrom=?1", nativeQuery = true)
    public List<ChatHistory> findhistoryforme(String messageto);


    //插入一条聊天记录
    @Query(value = "insert into chathistory (messagefrom, messageto, record) values ( ?1 , ?2 , ?3 )",nativeQuery = true)
    @Modifying
    public void storechathistory(String messagefrom,String messageto,String record);




    //根据id删除聊天记录
    @Query(value = "delete from chathistory where (messagefrom=?1 and messageto=?2) or (messagefrom=?2 and messageto=?1)", nativeQuery = true)
    @Modifying
    public void deleteChatHistories(String messagefrom,String messageto);


}
