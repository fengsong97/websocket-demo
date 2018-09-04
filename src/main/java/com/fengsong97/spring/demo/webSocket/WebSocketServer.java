package com.fengsong97.spring.demo.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * fengsong
 * 2018年8月17日14:44:14
 */
//websocket连接URL地址和可被调用配置
@Component
@ServerEndpoint(value="/webSocket/{userId}")
public class WebSocketServer {
    //日志记录
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //记录每个用户下多个终端的连接
    private static Map<String, Set<Session>> userSocket = new HashMap<>();

    /**
     * @Title: onOpen
     * @Description: websocekt连接建立时的操作
     * @param @param userId 用户id
     * @param @param session websocket连接的session属性
     * @param @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException{
        onlineCount++;
        //根据该用户当前是否已经在别的终端登录进行添加操作
        if (userSocket.containsKey(userId)) {
            logger.debug("当前用户id:{}已有其他终端登录",userId);
            for (Session sesn:userSocket.get(userId)) {
                if(sesn.isOpen()) continue;
                logger.debug("sessionId:{} 已关闭,所以删除该session",sesn.getId());
                userSocket.get(userId).remove(sesn);
                onlineCount--;
            }
            userSocket.get(userId).add(session); //增加该用户set中的连接实例
        }else {
            logger.debug("当前用户id:{}第一个终端登录",userId);
            Set<Session> addUserSet = new HashSet<>();
            addUserSet.add(session);
            userSocket.put(userId, addUserSet);
        }
        logger.debug("+++++++ 新增一个session, userId:{},sessionId:{}",userId,session.getId());
        logger.debug("用户{}登录的终端个数是为{}",userId,userSocket.get(userId).size());
        logger.debug("当前在线用户数为：{},所有终端个数为：{}",userSocket.size(),onlineCount);
    }

    /**
     * @Title: onClose
     * @Description: 连接关闭的操作
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId, Session session){
        //移除当前用户终端登录的websocket信息,如果该用户的所有终端都下线了，则删除该用户的记录
        if (userSocket.get(userId)!=null&&userSocket.get(userId).size() == 0) {
            userSocket.remove(userId);
        }else if(userSocket.get(userId)!=null&&userSocket.get(userId).size() != 0){
            userSocket.get(userId).remove(session);
            onlineCount--;
        }

        if(userSocket.get(userId)!=null&&userSocket.get(userId).size() == 0){
            userSocket.remove(userId);
        }
        logger.debug("----- 关闭一个session, userId:{},sessionId:{}",userId,session.getId());
        logger.debug("用户{}登录的终端个数是为{}",userId,userSocket.get(userId)!=null?userSocket.get(userId).size():"0");
        logger.debug("当前在线用户数为：{},所有终端个数为：{}",userSocket.size(),onlineCount);
    }

    /**
     * @Title: onMessage
     * @Description: 收到消息后的操作
     * @param @param message 收到的消息
     * @param @param session 该连接的session属性
     */
    @OnMessage
    public void onMessage(String message, @PathParam("userId") String userId, Session session) {
        logger.debug("收到来自用户id为：{}的消息：{}",userId,message);
        if(session ==null)  logger.debug("session null");
        sendMessageToUser(userId,message);
    }

    /**
     * @Title: onError
     * @Description: 连接发生错误时候的操作
     * @param @param session 该连接的session
     * @param @param error 发生的错误
     */
    @OnError
    public void onError(Session session, Throwable error, @PathParam("userId") String userId){
        logger.debug("用户id为：{}的连接发送错误",userId);
        error.printStackTrace();
    }

    /**
     * @Title: sendMessageToUser
     * @Description: 发送消息给用户下的所有终端
     * @param @param userId 用户id
     * @param @param message 发送的消息
     * @param @return 发送成功返回true，反则返回false
     */
    public Boolean sendMessageToUser(String userId,String message){
        if (userSocket.containsKey(userId)) {
            if(userSocket.get(userId).size()==0){
                logger.error("内部错误,userId:{}",userId);
            }
            for (Session session : userSocket.get(userId)) {
                if (!session.isOpen()) {
                    logger.debug("sessionId为:{}, 已关闭",session.getId());
                    continue;
                }
                logger.debug("sessionId为:{}, 正在发送",session.getId());
                try {
                    synchronized(session){
                        session.getBasicRemote().sendText(message);
                        logger.debug(" 给用户id为：{}的所有终端:sessionId:{},发送消息：{}",userId,session.getId(),message);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    logger.debug(" 给用户id为：{}发送消息失败",userId);
                    return false;
                }
            }
            return true;
        }
        logger.debug("发送错误：当前连接不包含id为：{} 的用户",userId);
        return false;
    }

}
