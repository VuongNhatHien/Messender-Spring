package com.example.spring_backend.attachment;

import com.example.spring_backend.exception.BadRequestException;
import com.example.spring_backend.shared.BaseService;
import com.example.spring_backend.shared.ErrorCode;
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
        return create(new Attachment(null, attachment.getOriginalFilename(), attachment.getContentType(), attachment.getSize()));
    }

    public Attachment updateAttachmentUrl(Long id, String url) {
        Attachment attachment = findById(id).orElseThrow(() -> new BadRequestException(ErrorCode.ATTACHMENT_NOT_FOUND));
        attachment.setUrl(url);
        return repository.save(attachment);
    }
}
