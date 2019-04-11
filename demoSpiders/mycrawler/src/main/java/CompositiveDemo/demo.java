package CompositiveDemo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用httpclient和jsoup解析页面信息
 */

public class demo {
    public static void main(String[] args) {

        //获取网站信息
        String url = "http://zhihudaily.me/";
        String rawHTML = null;
        try {
            //得到页面源代码
            rawHTML = getHTMLContent(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将当前页面转换成Jsoup的Document对象
        Document doc = Jsoup.parse(rawHTML);

        //获取所有元素的集合
        //注：Element类是用来构建xml中的节点的
        Elements days = doc.select("div[class=panel panel-primary]");

        //针对集合内每个元素进行解析并输出
        for( Element day : days ){
            //获取当日日期
            String data = day.select("span").text();
            System.out.println(data+"\n");

            //创建一个队列用来存标题
            Queue<String> titles = new LinkedList<>();

            //获取当日所有文章
            Elements dailies = day.select("a[class=list-group-item]");
            //获取各个文章的标题
            for( Element daily : dailies ){
                //将文章标题添加到队列
                titles.offer("第"+(dailies.indexOf(daily)+1)+"篇日报："+daily.text());
            }

            //依次输出该天文章中的标题
            for( String title : titles ){
                System.out.println(title);
            }

            //给它分隔一下
            System.out.println("--------------------------------------");
        }
    }

    //根据url地址获取对应页面的HTML内容（就把之前那个封装一下）
    private static String getHTMLContent( String url ) throws IOException {

        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //使用HttpGet方式请求网址
        HttpGet httpGet = new HttpGet(url);

        //获取网址的返回结果
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //获取返回结果中的实体
        HttpEntity entity = response.getEntity();

        String content = EntityUtils.toString(entity);

        //关闭HttpEntity流
        EntityUtils.consume(entity);

        return content;

    }
}
