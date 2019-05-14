package com.exc.rabbitmq.utils;

import com.rabbitmq.client.ConnectionFactory;

/**
 * 连接rabbitmq的工具
 */
public class RabbitUtil {
    private RabbitUtil() {
    }

    public static ConnectionFactory getConnectionFactory() {
        //创建连接工程，下面给出的是默认的case
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.153.129");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        return factory;
    }
}
