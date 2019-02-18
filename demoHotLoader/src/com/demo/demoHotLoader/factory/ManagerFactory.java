package com.demo.demoHotLoader.factory;

import com.demo.demoHotLoader.manager.loadInfo.DemoManagerLoadInfo;
import com.demo.demoHotLoader.loader.MyClassLoader;
import com.demo.demoHotLoader.manager.BaseManager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 作为加载manager的工厂啦
 */
public class ManagerFactory {

    // 记录热加载类的加载信息
    private static final Map<String, DemoManagerLoadInfo> loadTimeMap = new HashMap<>();

    // 要加载的类的classpath
    public static final String CLASS_PATH = "C://_myJava/demos/demoHotLoader/";

    // 实现类的热加载的全名称（包名+类名）
    public static final String MY_MANAGER = "com.demo.demoHotLoader.manager.impl.DemoManager";

    /**
     * 获取需要实现热部署的类（文件）
     * @param className 类名
     * @return
     */
    public static BaseManager getManager(String className) {

        // 获取需要进行热部署的文件
        File loadFile = new File(CLASS_PATH + className.replaceAll("\\.","/") + ".class");

        // 取得文件最后一次修改的时间
        long lastModified = loadFile.lastModified();

        // 若loadTimeMap中不包含以该className为键的信息，则说明该类没有被加载
        if(loadTimeMap.get(className) == null) {

            // 所以就加载它
            loadClass(className, lastModified);

        // 若该类已加载，但时间戳发生了改变，说明这个类被修改过了
        } else if(loadTimeMap.get(className).getLoadTime() != lastModified) {

            // 那么也需要重新加载它
            loadClass(className, lastModified);

        }

        return loadTimeMap.get(className).getManager();
    }

    /**
     * 加载这个类
     * @param className 类名
     * @param lastModified 最后修改时间
     */
    private static void loadClass(String className, long lastModified) {

        // 首先得到一个类加载器
        MyClassLoader myClassLoader = new MyClassLoader(CLASS_PATH);

        // 得到一个类的实例，这就是我们要加载的类了
        Class<?> loadClass = null;

        try {

            // 通过类名获取这个类，然后加载它
            loadClass = myClassLoader.loadClass(className);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 根据这个类获取BaseManager对象
        BaseManager baseManager = newInstance(loadClass);

        // 获取这个类的信息
        DemoManagerLoadInfo demoManagerLoadInfo = new DemoManagerLoadInfo(myClassLoader, lastModified);
        demoManagerLoadInfo.setManager(baseManager);

        // 将获取到的类信息放入Map
        loadTimeMap.put(className, demoManagerLoadInfo);
    }

    /**
     * 以反射的方式创建BaseManager对象
     * @param loadClass 要加载的类
     * @return
     */
    private static BaseManager newInstance(Class<?> loadClass) {
        try {

            return (BaseManager) loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
