package com.example.tadak.user.data;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResponseDto {
    private String nickname;
    private String email;
    private String loginType;
    private String accessToken;
}
