package com.example.spring_backend.rabbit;

import com.example.spring_backend.attachment.AttachmentService;
import com.example.spring_backend.rabbit.type.RabbitAttachmentType;
import com.example.spring_backend.services.StorageService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.File;

@RabbitListener(queues = "hello")
public class RabbitReceiver {
    @Autowired
    private StorageService storageService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitHandler
    public void receive(RabbitAttachmentType input) {
        System.out.println(" [x] Received " + input.getFilePath());
        File file = new File(input.getFilePath());
        Long attachmentId = input.getAttachmentId();
        String url = storageService.save(file);
        attachmentService.updateAttachmentUrl(attachmentId, url);
        System.out.println(" [x] Done " + input.getFilePath());
        String destination = "/chat/" + input.getChatId().toString();
        System.out.println(" [x] Destination " + destination);
        messagingTemplate.convertAndSend(destination, "done");

    }
}