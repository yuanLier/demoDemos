package picDemo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 爬取教务在线上的妹子图片并保存到本地
 */

public class PicDemo {
    public static void main(String[] args) throws Exception{
        Document document = getDocument("http://jwzx.cqupt.edu.cn/jwzxtmp/pubBjsearch.php?action=bjStu");
        //获取页面内全部链接
        Elements links = document.select("a[href]");
        //正好除第一条外均为班级链接，就从i=1遍历到完
        for(int i = 1; i < links.size(); i++) {
            Element link = links.get(i);
            System.out.println("链接 : " + link.attr("href"));
            System.out.println("班级 : " + link.text());
            //加上内网前缀，进入班级页面
            Document intoClass = getDocument("http://jwzx.cqupt.edu.cn/jwzxtmp/"+link.attr("href"));

            //获取该班所有学生的表格信息
            Elements studentsInfo = intoClass.select("tbody").select("tr");
            for(int j = 0; j < studentsInfo.size(); j++){
                //获取每个学生的单元格信息
                Elements studentInfo = studentsInfo.get(j).select("td");
                //获取该学生的性别
                String sex = studentInfo.get(3).text();
                //获取该学生的图片路径
                String picPath = studentInfo.get(10).select("img[src]").attr("src");
                //获取该学生的学号（想了想感觉直接用名字仿佛不是非常厚道的样子）
                String stuNo = studentInfo.get(1).text();

                //如果是妹子的话...
                if(sex.equals("女")){
                    System.out.println("链接：" + picPath);
                    //下载图片并保存到本地
                    downLoad(picPath, stuNo);
                }

                //还是要让它休息一会
                Thread.sleep(2000);
            }
        }

    }

    /**
     * 获取页面信息
     * @param url
     * @return
     * @throws Exception
     */
    public static Document getDocument(String url) throws Exception{
        Document document = Jsoup.connect(url).get();
        return document;
    }

    /**
     * 下载图片并保存到本地
     * @param imgSrc
     * @param picName
     * @throws Exception
     */
    public static void downLoad(String imgSrc,String picName) throws Exception{
        //读入
        URL url = new URL(imgSrc);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();

        //存储
        File file = new File("C://Users/yuanyiwen/Desktop/cqupt-pic/" + picName + ".jpg");
        FileOutputStream outputStream = new FileOutputStream(file);
        int n;
        while ((n = inputStream.read()) != -1){
            outputStream.write(n);
        }

        outputStream.close();
        inputStream.close();
    }
}

