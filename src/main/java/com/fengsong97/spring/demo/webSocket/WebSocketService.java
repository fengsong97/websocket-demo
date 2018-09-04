package com.fengsong97.spring.demo.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * fengsong
 * 2018年8月17日14:44:30
 */
@Service
public class WebSocketService {
    private Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    //声明websocket连接类
    @Autowired
    private WebSocketServer webSocketServer ;

    /**
     * @Title: sendToAllTerminal
     * @Description: 调用websocket类给用户下的所有终端发送消息
     * @param @param userId 用户id
     * @param @param message 消息
     * @param @return 发送成功返回true，否则返回false
     */
    public Boolean sendToAllTerminal(String userId,String message){
        logger.debug("向用户{}的消息：{}",userId,message);
        if(webSocketServer.sendMessageToUser(userId,message)){
            return true;
        }else{
            return false;
        }
    }
}
