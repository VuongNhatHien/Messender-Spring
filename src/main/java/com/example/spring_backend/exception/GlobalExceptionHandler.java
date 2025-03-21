package com.example.spring_backend.exception;

import com.example.spring_backend.shared.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ApiResponse<>(null, 500, "INTERNAL_SERVER_ERROR");
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException e) {
        ApiResponse<Void> response = new ApiResponse<>(null, e.getStatus(), e.getCode());
        return ResponseEntity.status(e.getStatus()).body(response);
    }

    // Handle error when validation failed
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ApiResponse<>(errors, 400, "VALIDATION_ERROR");
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiResponse<Void> handleBadCredentialsException(BadCredentialsException ex) {
//        return new ApiResponse<>(null, 400, "BAD_CREDENTIALS");
//    }
//
//    @ExceptionHandler(ExpiredJwtException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ApiResponse<Void> handleExpiredJwtException(ExpiredJwtException ex) {
//        return new ApiResponse<>(null, 401, "TOKEN_EXPIRED");
//    }
//
////    @ExceptionHandler(DataIntegrityViolationException.class)
////    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    public ApiResponse<Void> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
////        return  ApiResponse.<Void>builder()
////                .code(ErrorCode.VIDEO_NOT_FOUND.getCode())
////                .status(ErrorCode.VIDEO_NOT_FOUND.getStatus())
////                .build();
////    }
//
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
//        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
//                .body("File must be less than 100MB");
//    }
}

