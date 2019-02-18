package com.demo.demosprexcel.model.util.excel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelUtilTest {

    @Autowired
    ExcelUtil excelUtil;

    @Test
    public void exportExcel() {

        excelUtil.exportExcel(TableTitleEnum.USER_STUDENT, "C://Users/yuanyiwen/Desktop/demo.xlsx");
    }
}