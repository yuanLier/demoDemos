package com.demo.demosprexcel.model.excel;

import java.util.List;

/**
 * 父模板，供子模板注册时继承用的
 */
public class ExcelModel {

    // （从数据库）导出（到Excel）时表头，通常为中文
    public List<String> exportHead;

    // （从Excel）导入（到数据库）时表头，通常为英文
    public List<String> importHead;

    // （从数据库）导出（到Excel）时使用的SQL语句，通常为select语句
    public String exportSql;

    // （从Excel）导入（到数据库）时使用的SQL语句，通常为insert语句
    public String importSql;


}
