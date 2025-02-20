package com.example.spring_backend.metadata;

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
@Table(name = "metadata")
public class Metadata extends BaseEntity {
    private String title;
    private String image;
}
