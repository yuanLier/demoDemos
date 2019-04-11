package FormLoginDemo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过提交表单来模拟登陆
 */
public class LoginDemo {
    //建立请求客户端
    private static HttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) {
        //提交表单所需要的信息（在Chrome的network中查看(Form Data)）
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user_name", "15826770919"));
        params.add(new BasicNameValuePair("password", "123qwe"));

        //表单提交的地址
        String postMessage = "https://passport.julyedu.com/user_login";

        //提交表单信息
        post(postMessage,params);

        //验证登陆状态
        System.out.println(post(postMessage, params));

        /******************************到这里！你就！成功登陆了！*******************************/
        String url = "https://www.julyedu.com/question/select/kp_id/23";
        String html = get(url,params);

        //打印获取的网页信息
        System.out.println(html);
    }

    /**
     * 发送 get 请求
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, List<NameValuePair> params) {
        String body = null;
        try {
            // Get请求
            HttpGet httpget = new HttpGet(url);
            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null) {
                //关闭实体资源
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * 发送 post 请求
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, List<NameValuePair> params) {
        String body = null;
        try {
            // Post请求
            HttpPost httppost = new HttpPost(url);
            // 设置参数
            httppost.setEntity(new UrlEncodedFormEntity(params));
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httppost);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null) {
                //关闭实体资源
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
