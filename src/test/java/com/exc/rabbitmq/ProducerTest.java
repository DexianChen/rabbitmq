package com.exc.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.exc.rabbitmq.mq_enum.ProducerDataEnum;
import com.exc.rabbitmq.pojo.Student;
import com.exc.rabbitmq.utils.ProducerManager;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class ProducerTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        Student student = new Student();
        student.setId(1);
        student.setName("jack");
        student.setAge(24);

        ProducerManager.sendMessage(ProducerDataEnum.QUEUE_NAME.getValue(), ProducerDataEnum.EXCHANGE_NAME.getValue(),
                ProducerDataEnum.ROUTE_KEY.getValue(), JSON.toJSONString(student));
    }

}
