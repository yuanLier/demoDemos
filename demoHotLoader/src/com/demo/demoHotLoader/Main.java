package com.demo.demoHotLoader;


import com.demo.demoHotLoader.handler.MessageHandler;

/**
 * 测试Java热加载的实现
 */
public class Main {

    public static void main(String[] args) {

        // 启动我们的监测线程
        new Thread(new MessageHandler()).start();
    }
}
