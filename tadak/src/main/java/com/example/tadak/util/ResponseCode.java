package com.example.tadak.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK_SUCCESS(OK, "성공"),

    NOT_FOUND_USER(NOT_FOUND, "해당 유저를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
