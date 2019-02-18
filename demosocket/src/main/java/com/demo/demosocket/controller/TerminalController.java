package com.demo.demosocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.demosocket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demosocket")
public class TerminalController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public String sendMessage(@RequestParam Long userId, @RequestParam String message) {
        logger.info("向用户" + userId + "发送消息 ：" + message);

        if(messageService.sendMessageToOneUser(userId, message)){
            return "发送成功";
        } else {
            return "发送失败";
        }
    }
}
