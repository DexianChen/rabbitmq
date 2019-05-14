package com.exc.rabbitmq.consumer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exc.rabbitmq.consumer.Listener;
import com.exc.rabbitmq.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener1 implements Listener {
    private static final Logger logger = LoggerFactory.getLogger(Listener1.class);

    public void handleMessage(Object object) {
        Student jsonString = JSONObject.toJavaObject((JSON) object, Student.class);
        logger.info(jsonString.toString());
    }

}
