package com.example.spring_backend.user;

import com.example.spring_backend.shared.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    private String displayName;
    private String username;
    @JsonIgnore
    private String password;
    private String avatar;
    private String roles;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true; // Implement your logic if you need this
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true; // Implement your logic if you need this
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic if you need this
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true; // Implement your logic if you need this
    }
}