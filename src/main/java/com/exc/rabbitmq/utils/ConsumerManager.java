package com.exc.rabbitmq.utils;

import com.alibaba.fastjson.JSON;
import com.exc.rabbitmq.pojo.MyConsumer;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConsumerManager {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerManager.class);

    private static Address[] addresses = null;
    private static ConnectionFactory factory = null;
    //创建连接
    private static Connection connection = null;
    //创建信道
    private static Channel channel = null;

    static {
        try {
            factory = RabbitUtil.getConnectionFactory();
            connection = factory.newConnection(); //创建连接
            channel = connection.createChannel(); //创建信道
            channel.basicQos(64); // 设置客户端最多接收未被 ack 的消息的个数
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void initConsumer(List<MyConsumer> consumerList) throws IOException, TimeoutException {

        for (MyConsumer consumer : consumerList) {
            logger.info("开始启动{}消费者", consumer.getConsumerName());
            initSingleConsumer(consumer);
            logger.info("启动{}消费者成功", consumer.getConsumerName());
        }

    }

    private static void initSingleConsumer(MyConsumer myConsumer) throws IOException, TimeoutException {

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                Class<?> consumerClass = null;
                Method method = null;
                //处理消息体
                try {
                    consumerClass = Class.forName(myConsumer.getConsumerClass());
                    method = consumerClass.getDeclaredMethod("handleMessage", Object.class);
                    method.invoke(consumerClass.newInstance(), JSON.parseObject(new String(body)));
                } catch (ClassNotFoundException e) {
                    return;
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("找不到类" + consumerClass.getName() + "的" + method.getName() + "方法");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.SECONDS.sleep(1) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(myConsumer.getQueueName(), consumer);
    }

}
