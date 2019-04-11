package GsonDemo;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class SpiderDemo {
    public static void main(String[] args) {
        String url = "https://leetcode-cn.com/graphql?query=query%20getQuestionTranslation(%24lang%3A%20String)%20%7B%0A%20%20translations%3A%20allAppliedQuestionTranslations(lang%3A%20%24lang)%20%7B%0A%20%20%20%20title%0A%20%20%20%20question%20%7B%0A%20%20%20%20%20%20questionId%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A&operationName=getQuestionTranslation&variables=%7B%7D";

        Gson gson = new Gson();


        try {
            GsonTitleBean exercisesTitles = gson.fromJson(getJsons(url), GsonTitleBean.class);

            List<GsonTitleBean.DataInfo.TranslationInfo> translationInfos = exercisesTitles.data.translations;

            for(GsonTitleBean.DataInfo.TranslationInfo titles : translationInfos){
                System.out.println(titles.title);
                System.out.println(titles.question.questionId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getJsons( String url )throws Exception{
        //建立一个新的请求客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //使用HttpGet方式请求网址
        HttpGet httpGet = new HttpGet(url);
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
        String content  = EntityUtils.toString(entity);
        //关闭HttpEntity流
        EntityUtils.consume(entity);

        return content;
    }
}
