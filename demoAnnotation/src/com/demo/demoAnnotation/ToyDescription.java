package com.demo.demoAnnotation;

import java.lang.annotation.*;

/**
 * 这是一个用来描述玩具的注解
 */

// 只允许作用在方法上
@Target({ElementType.METHOD})
// 生存周期为RUNTIME
@Retention(RetentionPolicy.RUNTIME)
// 允许被继承
@Inherited
// 允许写入javadoc
@Documented
public @interface ToyDescription {

    // 玩具设计者
    String creator();

    // 玩具描述
    String desc();

    // 玩具售价
    float price() default 10f;
}



