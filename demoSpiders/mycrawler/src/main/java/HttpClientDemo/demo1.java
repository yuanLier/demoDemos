package HttpClientDemo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 代理设置
 * 使用httpclient获取页面源码
 */
public class demo1 {
    public static void main(String[] args) {
        //为请求客户端设置代理
        //设置代理IP、端口、协议
        HttpHost proxy = new HttpHost("localhost", 2055, "http");

        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();

        //建立请求客户端
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();


        //建立一个新的请求客户端
//        CloseableHttpClient httpClient = HttpClients.createDefault();

        //使用HttpGet方式请求网址
//        HttpGet httpGet = new HttpGet("https://www.julyedu.com/question/index");
        HttpGet httpGet = new HttpGet("https://www.google.com");
        //获取网址的返回结果
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取返回结果中的实体
        HttpEntity entity = response.getEntity();
        //将返回的实体输出
        try {
            System.out.println(EntityUtils.toString(entity));
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
