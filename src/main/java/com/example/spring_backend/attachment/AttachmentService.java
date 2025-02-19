package com.example.spring_backend.attachment;

import com.example.spring_backend.shared.BaseService;
import com.example.spring_backend.services.StorageService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentService extends BaseService<Attachment, Long> {
    private final AttachmentRepository attachmentRepository;
    private final StorageService storageService;


    public AttachmentService(JpaRepository<Attachment, Long> repository, AttachmentRepository attachmentRepository, StorageService storageService) {
        super(repository);
        this.attachmentRepository = attachmentRepository;
        this.storageService = storageService;
    }

    public Attachment createAttachment(MultipartFile attachment) {
        String url = storageService.save(attachment);
        return create(new Attachment(url, attachment.getOriginalFilename(), attachment.getContentType(), attachment.getSize()));
    }
}
