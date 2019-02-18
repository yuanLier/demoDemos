package com.demo.demoHotLoader.manager.impl;

import com.demo.demoHotLoader.manager.BaseManager;

/**
 * BaseManager的子类，该类需要实现Java热加载功能
 */
public class DemoManager implements BaseManager {

    @Override
    public void logic() {

        System.out.println("这是第一句话，但是被修改啦！");

        System.out.println("这还是第二句话");
    }
}
