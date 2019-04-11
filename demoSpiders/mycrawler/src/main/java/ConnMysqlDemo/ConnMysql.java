package ConnMysqlDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * jdbc驱动和动态注入（PreparedStatement）
 */
public class ConnMysql {
    public static void main(String arg[]) {
        try {
            //定义一个MYSQL连接对象
            Connection connection;

            //注入MySQL驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //连接本地MYSQL（参数格式：("jdbc:mysql://ip:端口/数据库名称", 用户名,密码)）
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/springmvc" +
                    "?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "root");
            //打印连接信息
            System.out.print("连接成功");

            //创建声明（PreparedStatement:允许动态注入）
            PreparedStatement preparedStatement;

            //创建sql语句（？：表示可变值）
            String sql = "insert into demo (id, name) values (4, ?)";

            //装载sql语句
            preparedStatement = connection.prepareStatement(sql);

            //表示给第一个问号处注入myName的值
            String myName = "wanger";
            preparedStatement.setString(1,myName);
            //执行SQL语句
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
