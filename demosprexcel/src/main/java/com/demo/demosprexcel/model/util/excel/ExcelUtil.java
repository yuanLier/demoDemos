package com.demo.demosprexcel.model.util.excel;

import com.demo.demosprexcel.model.excel.ExcelModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelUtil {

    private ExcelModelFactory excelModelFactory = new ExcelModelFactory();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 将数据从数据库导出到Excel
     * @param tableTitle 表标题
     * @param filePath 存储路径（注 ：如果要整合到工程里面，就把文件转化为流返给前端）
     */
    public void exportExcel(TableTitleEnum tableTitle, String filePath) {

        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet();
        //创建表头
        Row headRow = sheet.createRow(0);
        //为表头单元格填入数据
        Cell headCell;

        // 根据表标题得到Excel模板
        ExcelModel excelModel = excelModelFactory.createExcelModel(tableTitle);

        //将表头放入Excel
        for(int i = 0; i < excelModel.exportHead.size(); i++){
            headCell = headRow.createCell(i);
            headCell.setCellValue(excelModel.exportHead.get(i));
        }

        // 获取查询结果
        List<String> list = getQueryList(excelModel);

        //从第二行开始输入数据，共list.size/head.size行
        for(int i = 1; i <= list.size() / excelModel.exportHead.size(); i++){
            Row row = sheet.createRow(i);
            //每行创建head.size个单元格
            for (int j = 0; j < excelModel.exportHead.size(); j++) {
                Cell cell = row.createCell(j);
                //得到对应单元格的内容
                cell.setCellValue(list.get( ((i-1)*excelModel.exportHead.size() + (j+1)) -1 ));
            }
        }

        //创建文件
        File file = new File(filePath);
        try {
            file.createNewFile();

            //将创建好的工作簿写入文件流
            FileOutputStream stream = new FileOutputStream(file);
            workbook.write(stream);

            //关闭流
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据从Excel导入到数据库
     * @param tableTitle 表标题
     * @param multipartFile 前端传来的文件
     */
    public void importExcel(TableTitleEnum tableTitle, MultipartFile multipartFile) {
        // 根据表标题得到Excel模板
        ExcelModel excelModel = excelModelFactory.createExcelModel(tableTitle);

        try {
            // 获取工作簿及工作簿中的第一个sheet表
            XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            Object[] head;
            //遍历每行(getLastRowNum()的返回值为最后一行的索引，会比总行数少一行)
            for(int i = 0; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                //若该行非空(过滤空行)
                if (row != null) {
                    //在每行开始时初始化head，分配的空间数量即为要导入的表格的字段数
                    head = new Object[excelModel.importHead.size()];
                    // 先将所有单元格置为""（过滤空白单元格）
                    for(int j = 0; j < head.length; j++) {
                        head[j] = "";
                    }

                    //遍历每个单元格(getLastCellNum()的返回值值即为该行总列数)
                    for(int j = 0; j < row.getLastCellNum(); j++){
                        Cell cell = row.getCell(j);
                        //若单元格不为空（过滤空列）
                        if (cell != null) {
                            //将单元格类型转为String
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            //获取每个单元格的值
                            String value = cell.getStringCellValue();
                            //若单元格内容不为空
                            if (value != null) {
                                //添加该单元格的值
                                head[j] = value;
                            }
                        }
                    }

                    //存储单元格内容（不存储表头）
                    if (i > 0) {
                        // 根据表标题获取sql语句并注入
                        jdbcTemplate.update(excelModel.importSql, head);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据传入的Excel模板获取查询结果
     * @param excelModel
     * @return
     */
    private List<String> getQueryList(ExcelModel excelModel) {

        // 直接返回查询结果（两个参数分别为查询所需要的SQL语句，和匿名内部类ResultSetExtractor）
        // 这里将匿名内部类写成lambda表达式的形式了
        // 虽然我也不知道这个ResultSetExtractor具体是干啥的，但大概就是用来处理查询返回的数据集ResultSet的叭
        return jdbcTemplate.query(excelModel.exportSql, (ResultSet resultSet) -> {
            List<String> list = new ArrayList<>();
            while (resultSet.next()) {
                // 根据数据库中对应的存储字段，将数据集转化为list
                // 这个紫色高亮下划线表示lambda中的外部变量！还以为我叕哪写错了 哭哭
                for(String field: excelModel.importHead){
                    list.add(resultSet.getString(field));
                }
            }
            return list;
        });
    }
}
