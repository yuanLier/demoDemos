package com.demo.demoJMS.subjectPattern.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MyConsumer {

    private static final String url = "tcp://localhost:61616";

    private static final String topicName = "demoTopic";

    public static void main(String[] args) {

        // ConnectionFactory ：连接工厂，JMS用它创建连接
        ConnectionFactory connectionFactory;

        // Connection ：JMS客户端到JMS provider 的连接
        Connection connection = null;

        // Session ：一个发送或者接收的线程
        Session session;

        // Destination ：消息目的地，发送给谁接收
        Destination destination;

        // MessageConsumer：消息接收者，这里设置三个接受者
        MessageConsumer consumer1;
        MessageConsumer consumer2;
        MessageConsumer consumer3;

        // 创建连接工厂
        connectionFactory = new ActiveMQConnectionFactory(url);

        try {
            // 获取连接
            connection = connectionFactory.createConnection();

            // 启动连接
            connection.start();

            // 启动会话
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            // 创建Queue
            destination = session.createTopic(topicName);

            // 消费者1
            consumer1 = session.createConsumer(destination);
            consumer1.setMessageListener((message) -> {
                try {
                    System.out.println("主题模式 -- Consumer1收到消息 ：" + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            // 消费者2
            consumer2 = session.createConsumer(destination);
            consumer2.setMessageListener((message) -> {
                try {
                    System.out.println("主题模式 -- Consumer2收到消息 ：" + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            // 消费者3
            consumer3 = session.createConsumer(destination);
            consumer3.setMessageListener((message) -> {
                try {
                    System.out.println("主题模式 -- Consumer3收到消息 ：" + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            // 设置消费者的存活时间
            Thread.sleep(60 * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(null != connection){
                // 关闭连接
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
