package com.example.spring_backend.attachment;

import com.example.spring_backend.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "attachments")
public class Attachment extends BaseEntity {
    private String url;
    private String name;
    private String type;
    private Long size;
}
