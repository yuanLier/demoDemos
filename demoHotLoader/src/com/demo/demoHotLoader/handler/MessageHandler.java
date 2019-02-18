package com.demo.demoHotLoader.handler;

import com.demo.demoHotLoader.factory.ManagerFactory;
import com.demo.demoHotLoader.manager.BaseManager;

/**
 * 后台启动一条线程不断刷新，以及时重新加载实现了热加载的类
 */
public class MessageHandler implements Runnable {

    @Override
    public void run() {
        while (true) {

            // 获取BaseManager的子类对象（就是需要热加载的那个类啦）
            BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);

            // 然后对这个对象进行造作。。这里我们就简单的调用了一下它(可怜的唯一的)的方法
            manager.logic();

            // 休眠一秒以便于观察
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
