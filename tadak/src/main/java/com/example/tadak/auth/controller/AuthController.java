package com.example.tadak.auth.controller;

import com.example.tadak.auth.service.OAuthService;
import com.example.tadak.user.data.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tadak.user.data.SocialType.GOOGLE;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OAuthService oAuthService;

    @PostMapping("/auth/google")
    public ResponseEntity<LoginResponseDto> googleLogin(@RequestHeader("Authorization") String token) {
        return ResponseEntity
                .ok()
                .body(oAuthService.oauthLogin(token, GOOGLE));
    }
}
