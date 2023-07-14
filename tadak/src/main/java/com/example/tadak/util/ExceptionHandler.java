package com.example.tadak.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<ResponseTemplate> handleCustomException(CustomException e) {
        return ResponseTemplate.toResponseEntity(e.getResponseCode());
    }
}
