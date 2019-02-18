package com.demo.demosocket.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.demosocket.service.MessageService;
import com.demo.demosocket.terminal.UserTerminal;
import org.springframework.stereotype.Service;

@Service("webSocketMessageServer")
public class MessageServiceImpl implements MessageService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 声明一个用来发送消息的终端
    private UserTerminal terminal = new UserTerminal();

    /**
     * 向每个用户（的所有终端）发送消息
     * @param userId
     * @param message
     * @return
     */
    public boolean sendMessageToOneUser(Long userId, String message){
        logger.info("向用户" + userId + "发送消息 ：" + message);
        if(terminal.sendMessageToAllTerminals(userId, message)) {
            return true;
        } else {
            return false;
        }
    }
}
