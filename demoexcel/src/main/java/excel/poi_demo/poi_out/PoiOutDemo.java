package excel.poi_demo.poi_out;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * POI创建Excel
 * 支持xls/xlsx
 */

public class PoiOutDemo {
    public static void main(String[] args) {
        //表头
        String title[] = {"姓名","性别","学号"};

        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表sheet
        Sheet sheet = workbook.createSheet();

        //创建第一行
        Row titleRow = sheet.createRow(0);
        //为第一行设置表头
        Cell titleCell;
        for(int i = 0; i < title.length; i++){
            //i表示列数，整个坐标为第0行第i列
            titleCell = titleRow.createCell(i);
            titleCell.setCellValue(title[i]);
        }

        //输入测试数据
        for(int i = 1; i < 10+1; i++){
            Row row = sheet.createRow(i);
            Cell cell;
            cell = row.createCell(0);
            cell.setCellValue("刘岽");
            cell = row.createCell(1);
            cell.setCellValue("女");
            cell = row.createCell(2);
            cell.setCellValue("2016211005");
        }

        //创建文件
        File file = new File("C://Users/yuanyiwen/Desktop/poi_test.xlsx");
        FileOutputStream stream = null;
        try {

            file.createNewFile();
            //导入文件的输出流
            stream = FileUtils.openOutputStream(file);
            //将创建的工作簿写入文件流
            workbook.write(stream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
