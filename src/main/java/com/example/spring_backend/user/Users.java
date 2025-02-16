package com.example.spring_backend.user;

import com.example.spring_backend.shared.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users extends BaseEntity {
    private String displayName;
    private String username;
    @JsonIgnore
    private String password;
    private String avatar;
}