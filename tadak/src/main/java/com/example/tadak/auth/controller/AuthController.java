package com.example.tadak.auth.controller;

import com.example.tadak.auth.service.OAuthService;
import com.example.tadak.user.data.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tadak.user.data.SocialType.GOOGLE;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OAuthService oAuthService;

    @Operation(summary = "구글 로그인 API")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "로그인 성공") })
    @PostMapping("/auth/google")
    public ResponseEntity<LoginResponseDto> googleLogin(@Parameter(description = "구글 로그인 액세스 토큰") @RequestHeader("Authorization") String token) {
        return ResponseEntity
                .ok()
                .body(oAuthService.oauthLogin(token, GOOGLE));
    }
}
