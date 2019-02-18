package com.demo.demoDubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 首先保持zookeeper处于开启状态
 * 然后运行provider
 * 然后运行consumer
 */
public class Provider {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        context.start();

        System.out.println("Dubbo provider 启动中 φ(>ω<*) ");

        try {
            System.in.read();	// 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
