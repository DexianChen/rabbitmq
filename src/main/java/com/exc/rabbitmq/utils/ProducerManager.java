package com.exc.rabbitmq.utils;

import com.exc.rabbitmq.pojo.MyProducer;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ProducerManager {
    private static final Logger logger = LoggerFactory.getLogger(ProducerManager.class);

    private static ConnectionFactory factory = null;
    //创建连接
    private static Connection connection = null;
    //创建信道
    private static Channel channel = null;

    static{
        try {
            factory = RabbitUtil.getConnectionFactory();
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            logger.error("IOException");
        } catch (TimeoutException e) {
            logger.error("TimeoutException");
        }
    }

    private ProducerManager() {
    }

    public static void initProducer(List<MyProducer> producerList) throws IOException, TimeoutException {

        for (MyProducer producer : producerList) {
            logger.info("开始启动{}生产者", producer.getProducerName());
            initSingleProducer(producer);
            logger.info("启动{}生产者成功", producer.getProducerName());
        }
    }

    private static void initSingleProducer(MyProducer producer) throws IOException {
        // 创建一个 type="direct" 、持久化的、非自动删除的交换器
        channel.exchangeDeclare(producer.getExchangeName(), producer.getExchangeType(), true, false, null) ;
        //创建一个持久化、非排他的、非自动删除的队列
        channel. queueDeclare(producer.getQueueName(), true, false , false , null) ;
    }

    public static void sendMessage(String queueName, String exchangeName, String routeKey, String meaasge) throws IOException {
        //将交换器与队列通过路由键绑定
        channel.queueBind(queueName, exchangeName,  routeKey);
        //发送消息
        channel.basicPublish(exchangeName, routeKey, null, meaasge.getBytes());
        logger.info("发送信息{}成功", meaasge);
    }
}
