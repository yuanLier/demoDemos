package com.demo.demojedis;


import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class DemoJedis {

    @Test
    public void demo1() {
        // 设置IP地址和端口号
        Jedis jedis = new Jedis("192.168.43.163",6379);

        // 保存数据
        jedis.set("name","kangna");

        // 获取数据
        String valueOfName = jedis.get("name");
        System.out.println( "valueOfName = " + valueOfName);

        // 释放资源
        jedis.close();
    }

    @Test
    public void demo2() {
        // 获取连接池配置对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 然后设置一下，比如最大连接数、最大空闲连接数什么的
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxIdle(10);

        // 获取连接池
        JedisPool jedisPool = new JedisPool(poolConfig,"192.168.43.163",6379);

        // 获取Jedis对象
        Jedis jedis = null;
        try {
            // 通过连接池获取连接
            jedis = jedisPool.getResource();

            // 然后就是各种操作啦

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最后关闭资源
            if(jedis != null) {
                jedis.close();
            }
            if(jedisPool != null) {
                jedisPool.close();
            }
        }
    }


}
