package com.example.tadak.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class ResponseDataTemplate<T> {
    public int status;
    public String message;
    public T data;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static ResponseEntity<ResponseDataTemplate> toResponseEntity(ResponseCode responseCode, Object data) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(ResponseDataTemplate.builder()
                        .status(responseCode.getHttpStatus().value())
                        .message(responseCode.getDetail())
                        .data(data)
                        .build()
                );
    }
}