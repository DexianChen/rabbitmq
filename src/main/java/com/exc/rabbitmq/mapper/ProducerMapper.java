package com.exc.rabbitmq.mapper;

import com.exc.rabbitmq.pojo.MyProducer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerMapper {
    List<MyProducer> getProducerList();
}
