package com.demo.demoAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 解析 ToyAnnotation
 */
public class ParseToyDesc {
    public static void main(String[] args) {

        try {
            // 首先使用类加载器拿到类
            Class c = Class.forName("com.demo.demoAnnotation.Demo");

            // 然后拿到类中的所有方法
            Method[] methods = c.getMethods();

            // 对于这个类中的每个方法
            for (Method method : methods) {

                // 判断是否被加上了@ToyDescription这个注解
                boolean isExist = method.isAnnotationPresent(ToyDescription.class);

                // 如果是
                if (isExist) {
                    // 获取这个注解的全部信息，存入description
                    ToyDescription description = method.getAnnotation(ToyDescription.class);
                    // 然后就可以开始造作了！这里我们就简单地打印出这个玩具的描述信息叭(｀・ω・´)
                    System.out.println(description.desc());
                }

//                // 或者这样子 ：
//                // 取出加在该方法上的所有注解
//                Annotation[] annotations = method.getAnnotations();
//
//                // 对于每个注解
//                for (Annotation annotation : annotations) {
//
//                    // 判断它是否为某种自定义注解的实例
//                    if(annotation instanceof ToyDescription) {
//                
//                        // 如果是，获取这个注解的全部信息，存入description
//                        ToyDescription description = (ToyDescription) annotation;
//                        // 然后继续造作叭！
//                        System.out.println(description.price());
//                    }
//                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
