package com.demo.demosprexcel.model.excel;

import java.util.Arrays;

/**
 * user_teacher对应的模板
 */
public class UserTeacherExcelModel extends ExcelModel {

    public UserTeacherExcelModel() {

        this.exportHead = Arrays.asList("教师姓名", "教师工号", "性别");

        this.importHead = Arrays.asList("teacher_name", "teacher_num", "teacher_sex");

        this.exportSql = "select * from user_teacher;";

        this.importSql = "insert into user_teacher (teacher_name, teacher_num, teacher_sex) values (?, ?, ?);";
    }
}
