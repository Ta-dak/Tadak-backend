package com.example.tadak.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK_SUCCESS(OK, "성공"),

    BAD_REQUEST_INVALID_LOGIN_TYPE(BAD_REQUEST, "유효하지 않은 로그인 타입입니다."),

    FORBIDDEN_TOKEN_NOT_VALID(FORBIDDEN, "유효하지 않은 토큰입니다."),
    FORBIDDEN_TOKEN_EXPIRED(FORBIDDEN, "만료된 토큰입니다."),

    NOT_FOUND_USER(NOT_FOUND, "해당 유저를 찾을 수 없습니다."),

    SERVER_ERROR_CONNECTION(INTERNAL_SERVER_ERROR, "통신에 실패했습니다."),

    SERVER_ERROR_JSON_PARSING(INTERNAL_SERVER_ERROR, "서버에서 JSON 파싱을 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
