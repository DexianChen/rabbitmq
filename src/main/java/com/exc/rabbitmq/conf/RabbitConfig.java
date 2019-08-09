package com.exc.rabbitmq.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cdx
 * date 2019-08-09
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange");
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(helloQueue()).to(directExchange()).with("routeKey");
    }

}
