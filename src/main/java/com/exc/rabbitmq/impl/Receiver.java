package com.exc.rabbitmq.impl;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author cdx
 * date 2019-08-09
 */
@Service
@RabbitListener(queues = "hello")
public class Receiver {
    @RabbitHandler
    public void process(Object message) {
        System.out.println(message);
    }
}
