package com.demo.demohotdeploy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("hello")
    public String hello() {
        return "这是springboot的热部署！但是添加了依赖也没有用喵 总而言之还是得靠jrebel喵 而且jrebel会自动给你把auto那个打开的喵";
    }
}
