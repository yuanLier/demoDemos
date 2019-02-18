package com.demo.demosprexcel.controller;

import com.demo.demosprexcel.model.util.excel.ExcelUtil;
import com.demo.demosprexcel.model.util.excel.TableTitleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/demosprexcel")
public class DemoController {

    @Autowired
    ExcelUtil excelUtil;

    @PostMapping("/import")
    public void importExcel(TableTitleEnum tableTitle, @RequestParam MultipartFile multipartFile) {
        excelUtil.importExcel(tableTitle, multipartFile);
    }
}
