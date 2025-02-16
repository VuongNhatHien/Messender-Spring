package com.example.spring_backend.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    T data = null;
    Integer status = 200;
    String code = "SUCCESS";

    public ApiResponse(T data) {
        this.data = data;
    }
}
