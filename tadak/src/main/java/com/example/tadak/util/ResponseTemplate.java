package com.example.tadak.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
@AllArgsConstructor
public class ResponseTemplate {
    public int status;
    public String message;

    public static ResponseEntity<ResponseTemplate> toResponseEntity(ResponseCode responseCode) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(ResponseTemplate.builder()
                        .status(responseCode.getHttpStatus().value())
                        .message(responseCode.getDetail())
                        .build()
                );
    }
}
