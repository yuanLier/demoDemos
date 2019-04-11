package JulyDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {
    //判断是否为第一次打开的窗口
    private static boolean isFirst = true;

    //题目内容（content）
    private static String ques;
    //题目分析（analyse）
    private static String analysis;
    //选项相关（隶属于content）
    private static List<ExercisesBean.OptionInfo> options;

    //手动操作一下要爬取的方向和类型，对还有在题库中要点击的题型的xPath和url和循环次数times
    private static String ASPECT = "BasicCS";
    private static String TYPE = "ComPrinciple";
    private static String xPath = "//*[@id=\"depot\"]/div[2]/div[2]/ul[5]/li[6]/div/a";
    private static String url = "https://www.julyedu.com/Question/select/kp_id/5";
    private static int times = 3;

    public static void main(String[] args) {
        //添加Chrome的webDriver驱动
        System.setProperty("webdriver.chrome.driver","C://Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //先登陆
        driver.get("https://passport.julyedu.com/login");
        //选择登陆方式为账号登陆
        waitAMoment();
        driver.findElement(By.id("loginToAss")).click();
        //输入用户名和密码
        waitAMoment();
        driver.findElement(By.id("login_user_name")).sendKeys("13705971109");
        driver.findElement(By.id("login_pass_word")).sendKeys("123qwe");
        //确认登陆
        driver.findElement(By.id("login_to_ass")).click();

        //然后进入题库
        waitAMoment();
        driver.findElement(By.xpath("/html/body/div[1]/div/ul[1]/li[2]/a")).click();

        //好可以开始爬了
        try {
            //就让它一直爬吧 反正这个题型的爬完了会停下来的
            testConfirm(driver,xPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取HTML
     * @return
     */
    //直接用WebDriver取出
    private static String getHTML( WebDriver driver ) {
        //利用selenium直接返回当前页面的源码信息
        return driver.getPageSource();
    }

    /**
     * 从得到的HTML中分离出json
     * @param html
     * @return
     * @throws Exception
     */
    public static String getJsons(String html) throws Exception {
        String myJson = null;

        //通过正则表达式获取json文件
        //稍微多加一点点条件（ }] ）在;之前，避免提前结束
        String regexStr = "(?<=var data = )(.*?)(?=}];)";
        //获取Pattern对象
        Pattern pattern = Pattern.compile(regexStr);
        //定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            myJson = matcher.group();
        }

        //记得把 ] 加回来
        return myJson + "}]";
    }

    /**
     * 从json中解析出题目信息
     * @param html
     * @throws Exception
     */
    public static void getInfo(String html) throws Exception{
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(getJsons(html)).getAsJsonArray();

        //创建一个Gson对象
        Gson gson = new Gson();
        List<ExercisesBean> exercisesList = new ArrayList<>();

        for(JsonElement exercisesBean: jsonArray){
            ExercisesBean exercise = gson.fromJson(exercisesBean,ExercisesBean.class);
            exercisesList.add(exercise);
            
            //取出题干
            ques = exercise.getQues();
            //取出选项
            options = exercise.getOptions();
            //取出解析
            analysis = exercise.getAnalysis();

            char answer = 0;
            for(int i = 0; i < options.size(); i++){
                if(options.get(i).getIs_ans() == 1){
                    switch (i){
                        case 0:
                            answer = 'A';break;
                        case 1:
                            answer = 'B';break;
                        case 2:
                            answer = 'C';break;
                        case 3:
                            answer = 'D';break;
                    }
                }
            }

            //将爬取的题目存入数据库
            String content = null;
            if (options.size() == 4) {
                content = ques+"\nA."+options.get(0).getDes()+"\nB."+options.get(1).getDes() +
                        "\nC."+options.get(2).getDes()+"\nD."+options.get(3).getDes();
            }else if(options.size() == 3){
                content = ques+"\nA."+options.get(0).getDes()+"\nB."+options.get(1).getDes() +
                        "\nC."+options.get(2).getDes();
            }

            String analyse = "答案："+answer+"\n解析：\n"+analysis;
            connMysql(ASPECT,TYPE,content,analyse);
        }
    }

    /**
     * 连接数据库
     * @param myAspect
     * @param myType
     * @param myContent
     * @param myAnalyse
     */
    public static void connMysql(String myAspect,String myType,String myContent,String myAnalyse){

        //定义MYSQL连接对象
        Connection connection = null;
        //创建声明
        PreparedStatement preparedStatement = null;

        try {
            //注入驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //连接本地MYSQL
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/springmvc" +
                    "?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "root");

            //创建sql语句并装载
            String sql = "insert into exercises (title,aspect,type,content,analyse,accuracy,level,joined,righted) values (\"\",?,?,?,?,0,0,0,0)";
            preparedStatement = connection.prepareStatement(sql);
            //动态注入数据
            preparedStatement.setString(1,myAspect);
            preparedStatement.setString(2,myType);
            preparedStatement.setString(3,myContent);
            preparedStatement.setString(4,myAnalyse);

            //执行SQL语句
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //最后！记得关闭！
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用selenium在后台模拟浏览器操作，循环爬取
     * @param xPath 传入该方向题库的xPath
     * @throws Exception
     */
    public static void testConfirm (WebDriver driver,String xPath) throws Exception {
        //重定位到当前页面
        changeHandle(driver);
        //传入xPath，进入该类型题库
        waitAMoment();
        driver.findElement(By.xpath(xPath)).click();
        for (int i = 0; i < times; i++) {
            //获取页面源码并取出json文件，分析其中数据
            getInfo(getHTML(driver));
            //点击提交试卷
            waitAMoment();
            driver.findElement(By.className("select_submit")).click();
            //点击确定
            waitAMoment();
            driver.findElement(By.className("alert-true")).click();
            //跳转到“是否继续练习”页面，重定位
            changeHandle(driver);
            //不然就直接跳转页面好了
            driver.get(url);
        }

        //最后，记得关闭
        driver.quit();
    }

    /**
     * 让它休息一会，等页面加载完
     */
    public static void waitAMoment(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重定位
     * @param driver
     */
    public static void changeHandle(WebDriver driver){
        //等一会
        waitAMoment();
        //如果是第一个页面（由登陆跳转来的那个），更换句柄，关闭页面
        if (isFirst) {
            //得到当前窗口的句柄
            String currentWindow = driver.getWindowHandle();
            //得到所有窗口的句柄
            Set<String> handles = driver.getWindowHandles();
            for(String handle: handles){
                //若当前句柄仍为上一页面的句柄
                if(currentWindow.equals(handle)){
                    //关闭当前窗口
                    driver.close();
                    //让它不First
                    isFirst = false;
                    //跳出当前循环
                    continue;
                }
                driver.switchTo().window(handle);
            }
        }else {
            //如果不是，刷新页面
            driver.navigate().refresh();
        }
        //再等一会
        waitAMoment();
    }
}
