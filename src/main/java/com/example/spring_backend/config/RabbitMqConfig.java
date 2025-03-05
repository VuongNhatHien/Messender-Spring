package com.example.spring_backend.config;

import com.example.spring_backend.rabbit.RabbitReceiver;
import com.example.spring_backend.rabbit.RabbitSender;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue fileQueue() {
        return new Queue("file");
    }

    @Bean
    public RabbitReceiver receiver() {
        return new RabbitReceiver();
    }

    @Bean
    public RabbitSender sender() {
        return new RabbitSender();
    }

    @Bean
    public SimpMessageSendingOperations messagingTemplate(SimpMessageSendingOperations template) {
        return template;
    }
}
