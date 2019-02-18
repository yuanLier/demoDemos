package com.demo.demosprexcel.model.excel;

import java.util.Arrays;

/**
 * user_student对应的模板
 */
public class UserStudentExcelModel extends ExcelModel {

    public UserStudentExcelModel() {

        this.exportHead = Arrays.asList("姓名", "学号", "性别", "年龄");

        this.importHead = Arrays.asList("name", "num", "sex", "age");

        this.exportSql = "select * from user_student;";

        this.importSql = "insert into user_student (name, num, sex, age) values (?, ?, ?, ?);";
    }
}
