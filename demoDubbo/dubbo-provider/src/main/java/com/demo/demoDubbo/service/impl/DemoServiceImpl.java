package com.demo.demoDubbo.service.impl;


import com.demo.demoDubbo.service.DemoService;
import org.springframework.stereotype.Service;


@Service("demoService")
public class DemoServiceImpl implements DemoService {

    public String helloDubbo(String word) {
        return "hello " + word + "！";
    }

}
