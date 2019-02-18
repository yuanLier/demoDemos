package excel.poi_mix;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用poi实现Excel的导入导出
 * 支持xls/xlsx，封装成函数
 */
public class MixDemo {
    public static void main(String[] args) {
        String[] head = {"姓名","年龄"};
        importExcel("C://Users/yuanyiwen/Desktop/demo.xlsx",head);
//        exportExcel("C://Users/yuanyiwen/Desktop/demo.xlsx");
    }

    /**
     * 创建Excel
     * @param filePath 文件路径
     * @param head 表头
     */
    public static void importExcel(String filePath,String[] head){
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet();
        //创建表头
        Row headRow = sheet.createRow(0);
        //为表头单元格填入数据
        Cell headCell;
        for(int i = 0; i < head.length; i++){
            headCell = headRow.createCell(i);
            headCell.setCellValue(head[i]);
        }

        //从数据库中读取数据
        //todo

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
     * 解析Excel
     * @param filePath 文件路径
     */
    public static void exportExcel(String filePath){
        //需要解析的Excel文件
        File file = new File(filePath);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
            //获取第一个sheet表
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet.getLastRowNum());
            //遍历每行(getLastRowNum()的返回值为最后一行的索引，会比总行数少一行)
            for(int i = 0; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                //若该行非空(过滤空行)
                if (row != null) {
                    //遍历每个单元格(getLastCellNum()的返回值值即为该行总列数)
                    for(int j = 0; j < row.getLastCellNum(); j++){
                        Cell cell = row.getCell(j);
                        //若单元格不为空（过滤空列）
                        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                            //获取每个单元格的值
                            String value = cell.getStringCellValue();
                            //若单元格内容不为空（过滤空白单元格）
                            if (value != null) {
                                System.out.print(value + "\t");

                                //存入数据库
                                //TODO

                            }
                        }else{
                            System.out.print("\t");
                        }
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


