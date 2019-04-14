package com.exc.rabbitmq.constant;

/**
 * 生产者队列数据枚举
 */
public enum ProducerDataEnum {
    QUEUE_NAME("consumerQueue1"),
    EXCHANGE_NAME("exchange1"),
    ROUTE_KEY("routeKey1")
    ;

    private String value;

    ProducerDataEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}