package com.demo.demosocket.service;

public interface MessageService {
    /**
     * 向某一用户（的所有终端）发送消息
     * @param userId
     * @param message
     * @return
     */
    boolean sendMessageToOneUser(Long userId, String message);
}
