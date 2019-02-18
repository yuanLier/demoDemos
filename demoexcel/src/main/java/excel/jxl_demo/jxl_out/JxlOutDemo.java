package excel.jxl_demo.jxl_out;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

/**
 * JXL创建Excel
 */

public class JxlOutDemo {
    public static void main(String[] args) {
        //表头
        String[] title = {"姓名","性别","学号"};

        //创建Excel文件
        File file = new File("C://Users/yuanyiwen/Desktop/jxl_test.xls");

        try {

            //创建工作簿
            file.createNewFile();
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            //创建sheet1（从第一行开始）
            WritableSheet sheet = workbook.createSheet("sheet1",0);

            //为第一行设置表头（列名）
            Label label;
            for(int i = 0; i < title.length; i++){
                //三个属性分别为：列、行、输入的字符串
                label = new Label(i,0,title[i]);
                //添加单元格
                sheet.addCell(label);
            }

            //输入测试数据
            for(int i = 1; i < 10+1; i++){
                label = new Label(0,i,"刘岽");
                sheet.addCell(label);
                label = new Label(1,i,"男");
                sheet.addCell(label);
                label = new Label(2,i,"2016210003");
                sheet.addCell(label);
            }

            //向工作簿中写入数据
            workbook.write();
            //关闭数据流
            workbook.close();

            System.out.println("操作成功！");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
