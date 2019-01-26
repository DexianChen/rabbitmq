package com.exc.rabbitmq.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class MyConsumer {
    private Integer consumerId;
    private String consumerName;
    private String consumerClass;
    private String queueName;
    private String exchangeName;
    private String routeKey;
    private String exchangeType;
    private Date createTime;
}
