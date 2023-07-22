package com.example.tadak.user.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private String nickname;
    private String accessToken;
}
