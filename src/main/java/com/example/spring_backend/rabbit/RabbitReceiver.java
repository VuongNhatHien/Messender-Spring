package com.example.spring_backend.rabbit;

import com.example.spring_backend.chat.ChatService;
import com.example.spring_backend.chat.type.SendAttachmentType;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "hello")
public class RabbitReceiver {
    @Autowired
    private ChatService chatService;

    @RabbitHandler
    public void receive(SendAttachmentType input) {
        System.out.println(" [x] Received " + input.getFilePath());
        var res = chatService.sendAttachment(input);
        System.out.println(" [x] Done" + input.getFilePath());
    }
}
