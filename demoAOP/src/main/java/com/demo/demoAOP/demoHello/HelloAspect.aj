package com.demo.demoAOP.demoHello;

public aspect HelloAspect {

    // 前置通知
    pointcut demoBeforeAdvice() : execution(* Hello.sayHello(..));

    // 后置通知
    pointcut demoAfterAdvice() : execution(* Hello.sayHello(..));

    // 带返回值的后置通知
    pointcut demoAfterReturningAdvice() : execution(* Hello.sayHello(..));

    // 异常通知
    pointcut demoAfterExceptionAdvice() : execution(* Hello.sayHello(..));

    // 环绕通知
    pointcut demoAroundAdvice() : call(* Hello.sayHello(..));


    // 前置通知
    before() : demoBeforeAdvice() {
        System.out.println("这是一个前置通知");
    }

    // 后置通知
    after() : demoAfterAdvice() {
        System.out.println("这是一个后置日志");
    }

    // 带返回值的后置通知
    after() returning(String message) : demoAfterReturningAdvice() {
        System.out.println("返回的消息是 ：" + message);
    }

    // 异常通知
    after() throwing(Exception e) : demoAfterExceptionAdvice() {
        System.out.println("出现异常啦！异常信息如下 ：" + e.getMessage());
    }

    // 环绕通知
    Object around() : demoAroundAdvice() {
        // 执行前动作
        System.out.println("在执行前环绕一下");
        // 执行方法
        Object result = proceed();
        // 执行后动作
        System.out.println("在执行后环绕一下");

        return result;
    }
}