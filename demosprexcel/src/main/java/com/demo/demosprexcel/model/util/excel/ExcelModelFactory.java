package com.demo.demosprexcel.model.util.excel;

import com.demo.demosprexcel.model.excel.ExcelModel;
import com.demo.demosprexcel.model.excel.UserStudentExcelModel;
import com.demo.demosprexcel.model.excel.UserTeacherExcelModel;

/**
 * 模板工厂，根据已注册模板的枚举类型提供模板实体
 */

public class ExcelModelFactory {

    private ExcelModel excelModel;

    public ExcelModel createExcelModel(TableTitleEnum tableTitle) {

        switch (tableTitle) {
            case USER_STUDENT:

                excelModel = new UserStudentExcelModel();
                break;

            case USER_TEACHER:

                excelModel = new UserTeacherExcelModel();
                break;
        }

        return excelModel;
    }

}
