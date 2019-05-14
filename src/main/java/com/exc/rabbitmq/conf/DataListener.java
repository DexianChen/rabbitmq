package com.exc.rabbitmq.conf;

import com.exc.rabbitmq.mapper.ConsumerMapper;
import com.exc.rabbitmq.mapper.ProducerMapper;
import com.exc.rabbitmq.pojo.MyConsumer;
import com.exc.rabbitmq.pojo.MyProducer;
import com.exc.rabbitmq.utils.ConsumerManager;
import com.exc.rabbitmq.utils.ProducerManager;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
@MapperScan(basePackages = {"com.exc.rabbitmq.mapper"})
public class DataListener implements ApplicationContextAware{
    private static final Logger logger = LoggerFactory.getLogger(DataListener.class);

    @Autowired
    private ProducerMapper producerMapper;
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        try {
            //初始化生产者
            List<MyProducer> producerList = producerMapper.getProducerList();
            ProducerManager.initProducer(producerList);

            //初始化消费者
            List<MyConsumer> consumerList = consumerMapper.getConsumerList();
            ConsumerManager.initConsumer(consumerList);
        } catch (IOException e) {
            logger.error("IOException");
        } catch (TimeoutException e) {
            logger.error("TimeoutException");
        }
    }
}
