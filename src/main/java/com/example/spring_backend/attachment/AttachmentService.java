package com.example.spring_backend.attachment;

import com.example.spring_backend.shared.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentService extends BaseService<Attachment, Long> {
    private final AttachmentRepository attachmentRepository;

    public AttachmentService(JpaRepository<Attachment, Long> repository, AttachmentRepository attachmentRepository) {
        super(repository);
        this.attachmentRepository = attachmentRepository;
    }

    public Attachment createAttachment(MultipartFile attachment) {
        String url = "https://picsum.photos/id/237/200/300";
        return create(new Attachment(url, attachment.getOriginalFilename(), attachment.getContentType(), attachment.getSize()));
    }
}
