package excel.poi_demo.poi_in;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

/**
 * POI解析Excel
 * 支持xls/xlsx
 */

public class PoiInDemo {
    public static void main(String[] args) {
        //需要解析的Excel文件
        File file = new File("C://Users/yuanyiwen/Desktop/poi_test.xlsx");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
            //根据索引获取第一个sheet表(索引为0)
            Sheet sheet = workbook.getSheetAt(0);
            int firstRowNum = 0;
            //获取sheet中最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            //遍历每行
            for(int i = firstRowNum; i <= lastRowNum; i++){
                //获取当前行
                Row row = sheet.getRow(i);
                //获取当前行中最后单元格的列号
                int lastCellNum = row.getLastCellNum();
                //遍历每个单元格
                for(int j = 0; j < lastCellNum; j++){
                    Cell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
