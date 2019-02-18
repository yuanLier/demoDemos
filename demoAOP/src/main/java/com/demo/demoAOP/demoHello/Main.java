package com.demo.demoAOP.demoHello;

public class Main {

    public static void main(String[] args) {

        Hello hello = new Hello();

        System.out.println("main中执行前");
        hello.sayHello("AOP");
        System.out.println("main中执行后");
    }
}
