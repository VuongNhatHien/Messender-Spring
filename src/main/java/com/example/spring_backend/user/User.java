package com.example.spring_backend.user;

import com.example.spring_backend.shared.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends BaseEntity {
    private String displayName;
    private String username;
    @JsonIgnore
    private String password;
    private String avatar;
}