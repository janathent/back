package com.house.back.service;


import com.house.back.dao.ChatHistoryDao;
import com.house.back.pojo.ChatHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChatHistoryService {
    @Autowired
    ChatHistoryDao chatHistoryDao;



    //根据ID查询聊天记录
    public List<ChatHistory> findhistory(String messagefrom, String messageto){
        //id1给i2发的消息
        List<ChatHistory> list = chatHistoryDao.findhistory(messagefrom,messageto);
        return list;
    }


    //查询是谁给我发了信息
    public List<ChatHistory> findhistoryforme(String messageto){
        List<ChatHistory> list = chatHistoryDao.findhistoryforme(messageto);
        return list;
    }




    //存储一条聊天记录
    public void storechathistory(String messagefrom,String messageto,String record){
        chatHistoryDao.storechathistory(messagefrom,messageto,record);
    }

    //根据id删除聊天记录
    public void deleteChatHistories(String messagefrom,String messageto){
        chatHistoryDao.deleteChatHistories(messagefrom,messageto);
    }

}
