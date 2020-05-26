package com.house.back.controller;


import com.house.back.pojo.ChatHistory;
import com.house.back.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ChatHistoryController {
    @Autowired
    @Resource
    ChatHistoryService chatHistoryService;



    //根据ID查找聊天记录
    @CrossOrigin
    @RequestMapping("api/findchathistory")
    @ResponseBody
    public List<ChatHistory> findchathistory(@RequestParam("messagefrom") String messagefrom,@RequestParam("messageto") String messageto){
        List<ChatHistory> list = chatHistoryService.findhistory(messagefrom,messageto);
        return list;
    }


    //储存聊天记录
    @CrossOrigin
    @RequestMapping("api/storechathistory")
    @ResponseBody
    public String storechathistory(@RequestParam("messagefrom") String messagefrom,@RequestParam("messageto") String messageto,@RequestParam("record") String record){
        chatHistoryService.storechathistory(messagefrom,messageto,record);
        return "ok";
    }


    //传来的ID1 ID2 删除聊天记录
    @CrossOrigin
    @RequestMapping("api/deletechathistories")
    @ResponseBody
    public String deletechathistories(@RequestParam("messagefrom") String messagefrom,@RequestParam("messageto") String messageto){
        chatHistoryService.deleteChatHistories(messagefrom,messageto);
        return "ok";
    }



    //查询有谁给我发了消息
    @CrossOrigin
    @RequestMapping("api/findchathistoryforme")
    @ResponseBody
    public List<ChatHistory> findchathistoryforme(@RequestParam("messageto") String messageto){
        List<ChatHistory> list = chatHistoryService.findhistoryforme(messageto);
        return list;
    }

}
