package com.example.spring_backend.rabbit;

import com.example.spring_backend.rabbit.type.RabbitAttachmentType;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitSender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(RabbitAttachmentType input) {
        this.template.convertAndSend(queue.getName(), input);
        System.out.println(" [x] Sent " + input.getFilePath());
    }
}
