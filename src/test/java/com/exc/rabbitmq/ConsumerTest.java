package com.exc.rabbitmq;

import com.exc.rabbitmq.utils.ConsumerManager;
import com.exc.rabbitmq.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        Connection connection = factory.newConnection();//创建连接
        Channel channel = connection.createChannel();//创建信道
        channel.basicQos(64); // 设置客户端最多接收未被 ack 的消息的个数
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println( "recv message: " + new String(body)) ;
                try {
                    TimeUnit.SECONDS.sleep(1) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume("producerQueue1", consumer);
        //等待回调函数执行完毕之后 ， 关闭资源
//        TimeUnit.SECONDS.sleep(5);
//        channel.close();
//        connection.close();
    }
}
