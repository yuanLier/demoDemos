package lambda;

import lombok.Data;

@Data
public class Member {
    private String name;
    private String sex;
    private int age;

    public Member(String name,String sex,int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
