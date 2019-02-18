package excel.jxl_demo.jxl_in;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * JXL解析Excel
 */

public class JxlInDemo {
    public static void main(String[] args) {
        try {
            //创建工作簿
            Workbook workbook = Workbook.getWorkbook(new File("C://Users/yuanyiwen/Desktop/jxl_test.xls"));
            //获取第一个工作表sheet1
            Sheet sheet = workbook.getSheet(0);
            //获取数据
            for(int i = 0; i < sheet.getRows(); i++){
                for(int j = 0; j < sheet.getColumns(); j++){
                    Cell cell = sheet.getCell(j,i);
                    System.out.print(cell.getContents() + "\t");
                }
                System.out.println();
            }
            //关闭数据流
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
