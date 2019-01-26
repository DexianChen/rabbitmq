package com.exc.rabbitmq.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class MyProducer {
    private Integer producerId;
    private String producerName;
    private String queueName;
    private String exchangeName;
    private String routeKey;
    private String exchangeType;
    private Date createTime;
}
