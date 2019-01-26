package com.exc.rabbitmq.mapper;

import com.exc.rabbitmq.pojo.MyConsumer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerMapper {
    List<MyConsumer> getConsumerList();
}
