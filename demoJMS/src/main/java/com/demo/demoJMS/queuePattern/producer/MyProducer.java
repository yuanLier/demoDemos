package com.demo.demoJMS.queuePattern.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MyProducer {

    private static final String url = "tcp://localhost:61616";

    private static final String queueName = "demoQueue";

    private static final int SEND_NUMBER = 10;

    public static void main(String[] args) {

        // ConnectionFactory ：连接工厂，JMS用它创建连接
        ConnectionFactory connectionFactory;

        // Connection ：JMS客户端到JMS provider的连接
        Connection connection = null;

        // Session ：一个发送或者接收消息的线程
        Session session;

        // Destination ：消息发送目的地，消息发送给谁接收
        Destination destination;

        // MessageProducer ：消息发送者
        MessageProducer producer;

        // 构造ConnectionFactory 实例对象，此处采用ActiveMQ的实现jar
        connectionFactory = new ActiveMQConnectionFactory(url);

        try {
            // 获取连接
            connection = connectionFactory.createConnection();

            // 启动连接
            connection.start();

            // 启动会话（true代表开启事务）
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            // 创建Queue
            destination = session.createQueue(queueName);

            // 创建生产者
            producer = session.createProducer(destination);

            // 发送消息
            sendMessage(session, producer);

            // 提交session
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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

    private static void sendMessage(Session session, MessageProducer producer)throws Exception {
        for (int i = 0; i < SEND_NUMBER; i++) {
            TextMessage message = session.createTextMessage("队列模式 -- Producer发消息来啦！这是第" + i + "条消息");
            // 发送消息
            System.out.println(" 队列模式 -- Producer ：正在发送第" + i + "条消息");
            producer.send(message);
        }
    }

}
