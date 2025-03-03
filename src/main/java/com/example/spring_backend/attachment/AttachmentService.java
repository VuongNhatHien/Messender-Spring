package com.example.spring_backend.attachment;

import com.example.spring_backend.services.StorageService;
import com.example.spring_backend.shared.BaseService;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;

@Service
public class AttachmentService extends BaseService<Attachment, Long> {
    private final AttachmentRepository attachmentRepository;
    private final StorageService storageService;


    public AttachmentService(JpaRepository<Attachment, Long> repository, AttachmentRepository attachmentRepository, StorageService storageService) {
        super(repository);
        this.attachmentRepository = attachmentRepository;
        this.storageService = storageService;
    }

    public Attachment createAttachment(File file) {
        String originalFilename = file.getName();
        long fileSize = file.length();
        String contentType = getContentType(file);

        String url = storageService.save(file);

        return create(new Attachment(url, originalFilename, contentType, fileSize));
    }

    @SneakyThrows
    private String getContentType(File file) {
            return Files.probeContentType(file.toPath());

    }
}
