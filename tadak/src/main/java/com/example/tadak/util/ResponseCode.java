package com.example.tadak.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK_SUCCESS(OK, "성공");

    private final HttpStatus httpStatus;
    private final String detail;
}
