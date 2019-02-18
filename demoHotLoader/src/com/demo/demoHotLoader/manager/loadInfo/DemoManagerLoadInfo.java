package com.demo.demoHotLoader.manager.loadInfo;

import com.demo.demoHotLoader.loader.MyClassLoader;
import com.demo.demoHotLoader.manager.BaseManager;

/**
 * 封装加载类的信息
 */
public class DemoManagerLoadInfo {

    // 这是我们自定义的类加载器
    private MyClassLoader myLoader;

    // 记录要加载的类的时间戳（它记录的是加载的时间）
    private Long loadTime;

    // 这其实就是那个要加载的类，只不过这里我们使用的是它的父类，有利于封装
    private BaseManager manager;

    public DemoManagerLoadInfo(MyClassLoader myLoader, Long loadTime) {
        this.myLoader = myLoader;
        this.loadTime = loadTime;
    }

    public MyClassLoader getMyLoader() {
        return myLoader;
    }

    public void setMyLoader(MyClassLoader myLoader) {
        this.myLoader = myLoader;
    }

    public Long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Long loadTime) {
        this.loadTime = loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }
}
