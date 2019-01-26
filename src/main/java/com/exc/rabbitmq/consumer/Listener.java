package com.exc.rabbitmq.consumer;

public interface Listener {
    /**
     * 处理消息体
     * @param object 消息体
     */
    void handleMessage(Object object) throws Exception;
}
