package com.fengsong97.spring.demo.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * fengsong
 * 2018年8月17日14:43:57
 */
@Controller
@RequestMapping("/message")
public class WebSocketController  {
    private Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    //websocket服务层调用类
    @Autowired
    private WebSocketService webSocketService;

    //请求入口
    @GetMapping(value="/test")
    @ResponseBody
    public String TestWS(@RequestParam(value="userId",required=true) String userId,
                         @RequestParam(value="message",required=true) String message){
        logger.debug("收到发送请求，向用户{}的消息：{}",userId,message);
        if(webSocketService.sendToAllTerminal(userId, message)){
            return "发送成功";
        }else{
            return "发送失败";
        }
    }
}
