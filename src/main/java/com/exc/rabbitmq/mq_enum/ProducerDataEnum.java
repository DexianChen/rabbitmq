package com.exc.rabbitmq.mq_enum;

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
