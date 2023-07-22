package com.example.tadak.auth.controller;

import com.example.tadak.auth.service.OAuthService;
import com.example.tadak.user.data.LoginResponseDto;
import com.example.tadak.user.data.SocialType;
import com.example.tadak.util.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tadak.user.data.SocialType.GOOGLE;
import static com.example.tadak.util.ResponseCode.OK_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final OAuthService oAuthService;

    @Operation(summary = "소셜 로그인")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "소셜 로그인 성공"),
            @ApiResponse(responseCode = "403", description = "유효하지 않은 토큰") })
    @PostMapping("/{social-type}")
    public ResponseEntity<ResponseTemplate> socialLogin(@RequestHeader("Authorization") String token,
                                                        @PathVariable("social-type") String socialName) {
        oAuthService.oauthLogin(token, SocialType.getSocialTypeBySocialName(socialName));
        return ResponseTemplate.toResponseEntity(OK_SUCCESS);
    }
}
