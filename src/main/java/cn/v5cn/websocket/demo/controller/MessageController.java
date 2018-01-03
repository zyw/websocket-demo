package cn.v5cn.websocket.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @ResponseBody
    @GetMapping("/send.message")
    public Object sendPublicMessage(String msg,String user) {
        simpMessagingTemplate.convertAndSendToUser(user,"/message",msg);
        Map<String,String> aa = new HashMap<>();
        aa.put("key","aaa");
        return aa;
    }

    @MessageMapping("/send.message")
    public void sendPublicMessage(String msg) {
        simpMessagingTemplate.convertAndSendToUser("zhangsan","/message",msg);
        simpMessagingTemplate.convertAndSend("/topic",msg);
    }

    @GetMapping("/index")
    public String index(String userId, ModelMap modelMap) {
        modelMap.put("userId",userId);
        return "index";
    }
}
