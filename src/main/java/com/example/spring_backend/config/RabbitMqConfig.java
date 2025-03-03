package com.example.spring_backend.config;

import com.example.spring_backend.rabbit.RabbitReceiver;
import com.example.spring_backend.rabbit.RabbitSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean
    public RabbitReceiver receiver() {
        return new RabbitReceiver();
    }

    @Bean
    public RabbitSender sender() {
        return new RabbitSender();
    }
}
