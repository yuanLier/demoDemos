package com.demo.demoHotLoader.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义加载类加载器，以实现Java的热加载
 */
public class MyClassLoader extends ClassLoader {

    /**
     * 要加载的类的classpath路径
     */
    private String classpath;

    public MyClassLoader(String classpath) {
        super(ClassLoader.getSystemClassLoader());
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] data = this.loadClassData(name);

        // 将字节数组data中的内容转化为Java类，返回结果是一个java.lang.Class类的实例
        return this.defineClass(name, data, 0, data.length);
    }

    /**
     * 加载class文件中的内容
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        try {

            // 获取文件名
            name = name.replace(".", "//");

            // 构建文件输入流
            FileInputStream inputStream = new FileInputStream(new File(classpath + name + ".class"));
            // 构建字符数组输出流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // 读取文件
            int b = 0;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }

            // 关闭文件输入流
            inputStream.close();

            // 将该输出流转化为字符数组并返回
            return outputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
