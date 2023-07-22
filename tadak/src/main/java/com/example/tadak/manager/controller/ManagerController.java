package com.example.tadak.manager.controller;

import com.example.tadak.manager.service.ManagerService;
import com.example.tadak.util.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.tadak.util.ResponseCode.OK_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerservice;
    @PostMapping
    @RequestMapping("/auth/kakao")
    public ResponseEntity<?> kakaologin(@RequestParam(name = "code") String controllerCode) {
        String accessToken = managerservice.getKakaoAccessToken(controllerCode);
        System.out.println(accessToken);
        System.out.println(managerservice.getUserInfo(accessToken));
        return ResponseTemplate.toResponseEntity(
                OK_SUCCESS
        );
    }
}
