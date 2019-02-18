package com.demo.demoAOP.demoAnnotion;

public class Hello {

    public String sayHello(String name) {

//        System.out.println(2/0);

        System.out.println("hello " + name + "！");

        return name;
    }
}
