package com.example.tadak.manager.controller;

import com.example.tadak.manager.service.ManagerService;
import com.example.tadak.util.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tadak.util.ResponseCode.OK_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerservice;

    @Operation(summary = "카카오 Access Token")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "회원가입 성공") })
    @PostMapping("/auth/kakao")
    public ResponseEntity<ResponseTemplate> kakaologin(@RequestParam(name = "code") String controllerCode) {
        String accessToken = managerservice.getKakaoAccessToken(controllerCode);
        System.out.println(accessToken);
        System.out.println(managerservice.getUserInfo(accessToken));
        return ResponseTemplate.toResponseEntity(
                OK_SUCCESS
        );
    }

    @Operation(summary = "구글 로그인 Redirect API")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "회원가입 성공") })
    @GetMapping("/login/oauth2/code/{registrationId}")
    public void googleLoginRedirect(@RequestParam String code, @PathVariable String registrationId) {
        managerservice.getSocialAccessToken(code, registrationId);

    }
}