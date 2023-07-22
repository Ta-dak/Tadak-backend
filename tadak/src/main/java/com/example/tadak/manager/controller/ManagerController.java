package com.example.tadak.manager.controller;

import com.example.tadak.auth.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private OAuthService oAuthService;

    @Operation(summary = "구글 로그인 Redirect API")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "회원가입 성공") })
    @GetMapping("/login/oauth2/code/{registrationId}")
    public void googleLoginRedirect(@RequestParam String code, @PathVariable String registrationId) {
        oAuthService.getSocialAccessToken(code, registrationId);
    }
}
