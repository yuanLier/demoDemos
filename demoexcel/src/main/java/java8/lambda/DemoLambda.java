package java8.lambda;

public class DemoLambda {
    public static void main(String[] args) {
        // 声明类型
        MathOperation add = (int a, int b) -> a + b;

        // 不声明类型
        MathOperation sub = (a, b) -> a - b;

        // 有大括号及返回值
        MathOperation mul = (int a, int b) -> {return a * b;};

        // 没有大括号及返回值
        MathOperation div = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + add.operation(10, 5));
        System.out.println("10 - 5 = " + sub.operation(10, 5));
        System.out.println("10 * 5 = " + mul.operation(10, 5));
        System.out.println("10 / 5 = " + div.operation(10, 5));

        GreetingService greetingService = message -> System.out.println("hello " + message);

        greetingService.sayMessage("world!");

    }
}

@FunctionalInterface
interface MathOperation{
    int operation(int a,int b);

    default String sayHello() {
        return "hello world!";
    }
}

interface GreetingService{
    void sayMessage(String message);
}
