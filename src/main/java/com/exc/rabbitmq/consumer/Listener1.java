package com.exc.rabbitmq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exc.rabbitmq.pojo.Student;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Listener1 {

    public void handleMessage(Object object) throws IOException, TimeoutException {
        Student jsonString = JSONObject.toJavaObject((JSON) object, Student.class);
        System.out.println(jsonString.toString());
    }

}
