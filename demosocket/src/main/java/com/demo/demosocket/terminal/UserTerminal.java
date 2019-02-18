package com.demo.demosocket.terminal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * onlineCount 终端总数
 * userSocket 记录每个用户
 * userSocket中，一个userId对应一个Set集合 记录该用户的每个终端
 * this表示的当前终端啦，但是终端中是会记录着终端所属人的，所以this.userId指的是该用户
 */

@ServerEndpoint(value = "/demochat/{userId}")
public class UserTerminal {

    // 日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 静态变量，记录该时间上的在线连接数（即终端数）
    private static int onlineCount = 0;

    // 记录每个用户下终端的连接情况
    private static Map<Long, Set<UserTerminal>> userSocket = new HashMap<Long, Set<UserTerminal>>();

    // 通过session发送数据、获取userId
    private Session session;
    private Long userId;

    /**
     * 终端连接登录中
     * @param userId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session){
        this.session = session;
        this.userId = userId;

        // 终端数+1
        onlineCount++;

        // 判断该用户是否已经在其他终端登录
        if(userSocket.containsKey(this.userId)) {
            logger.info("用户" + this.userId + "已在其他终端登录");
            // 通过用户id将该终端加入此用户的终端总连接下
            userSocket.get(this.userId).add(this);
        } else {
            logger.info("这是用户" + this.userId + "的第一个终端登录");
            // 创建一个用户并为此用户加入该终端
            Set<UserTerminal> user = new HashSet<UserTerminal>();
            user.add(this);
            // 将该用户情况加入终端总连接下
            userSocket.put(this.userId, user);
        }

        logger.info("用户" + this.userId + "在线的终端个数为 ：" + userSocket.get(this.userId).size());
        logger.info("当前在线的用户总数为 ：" + userSocket.size() + "；终端总数为 ：" + onlineCount);
    }

    /**
     * 终端下线中
     */
    @OnClose
    public void onClose(){

        // 将该终端从用户终端中移除
        userSocket.get(this.userId).remove(this);
        // 终端数减一
        onlineCount--;

        // 一个终端下线后，将其从用户终端中移除
        if(userSocket.get(this.userId).size() == 0) {
            logger.info("用户" + this.userId + "所有终端已下线");
            // 若该终端为其最后一个终端，删除该用户
            userSocket.remove(this.userId);
        } else {
            logger.info("用户" + this.userId + "已从终端<session" + this.session.getId() + ">下线");
            logger.info("用户" + this.userId + "在线的终端个数为 ：" + userSocket.get(this.userId).size());
        }

        logger.info("当前在线的用户总数为 ：" + userSocket.size() + "；终端总数为 ：" + onlineCount);
    }

    /**
     * 消息发送中
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自" + this.userId + "的消息 ：" + message);

        if(session == null) {
            logger.info("session为空");
        }
    }

    /**
     * error发生中
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("用户" + this.userId + "发生错误" + error.getMessage());
    }


    /**
     * 向某一用户发送消息
     * @param userId
     * @param message
     * @return 若发送成功，返回true；反之，返回false
     */
    public boolean sendMessageToAllTerminals(Long userId, String message) {
        // 若该用户在线，发送消息到其所有终端
        if(userSocket.containsKey(userId)) {
            logger.info("向用户" + userId + "所有终端发送消息 ：" + message);
            // 遍历该用户所有终端，并向每个终端发送消息
            for(UserTerminal terminal : userSocket.get(userId)) {
                logger.info("该终端下的sessionId为 ：" + terminal.session.getId());
                try {
                    terminal.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    // 若发送时捕获到异常，返回false
                    logger.info("消息发送失败 ：" + e.getMessage());
                    return false;
                }
            }
            // 若向所有终端成功发送，返回true
            return true;
        }

        // 若该用户不存在，返回false
        logger.info("发送失败，用户" + userId + "不存在");
        return false;
    }

}
