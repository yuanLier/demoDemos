package com.demo.demoAnnotation;

public class Demo {

    public static void main(String[] args) {
        toyDuck();
    }

    @ToyDescription(creator = "大白", desc = "这是一只会叫的玩具鸭")
    public static void toyDuck() {
        System.out.println("嘎嘎嘎");
    }

}
