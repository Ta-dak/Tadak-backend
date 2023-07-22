package com.example.tadak.manager.controller;

import com.example.tadak.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private OAuthService oAuthService;

    @GetMapping("/login/oauth2/code/{registrationId}")
    public void googleLoginRedirect(@RequestParam String code, @PathVariable String registrationId) {
        oAuthService.getSocialAccessToken(code, registrationId);
    }
}
