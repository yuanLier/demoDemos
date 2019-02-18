package com.demo.demoAOP.demoAnnotion;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class HelloAspect {

    // 切点
    // 注 ：这里因为都是一个切点所以统一拎出来了，也可以单独写在value里
    @Pointcut("execution(* Hello.sayHello(..))")
    public void pointcut(){}


//    // 注 ：execution的时候环绕通知和前置通知有冲突，所以我们把前置注释掉叭！
//    // 前置通知
//    @Before(value = "pointcut()")
//    public void demoBeforeAspect() {
//        System.out.println("前置通知");
//    }


    // （带返回值的）后置通知
    // 注 ：也可以不传参，然后这个注解的returning属性就也可以不用加啦
    @AfterReturning(value = "pointcut()", returning = "returnValue")
    public void demoAfterReturningAspect(String returnValue) {
        System.out.println("后置通知 -- 得到的返回信息如下 ：" + returnValue);
    }


    // （返回异常信息的）异常通知
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void demoAfterExceptionAspect(Exception e) {
        System.out.println("异常通知 -- 得到的异常信息如下 ：" + e.getMessage());
    }


    // 环绕通知
    @Around(value = "pointcut()")
    public Object demoAroundAspect(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("环绕通知前");
        Object object = joinPoint.proceed();
        System.out.println("环绕通知后");
        return object;
    }


    // 无论什么情况都会执行的最终通知
    @After(value = "pointcut()")
    public void demoAfterAspect() {
        System.out.println("最终通知");
    }

}
