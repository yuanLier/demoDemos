package com.demo.demoDubbo;

import com.demo.demoDubbo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/spring.xml"});
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");

        System.out.println(demoService.helloDubbo("Dubbo"));

        try {
            System.in.read();	// 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
