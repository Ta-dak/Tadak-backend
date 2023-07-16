package com.example.tadak.user.controller;

import com.example.tadak.user.data.SignupRequestDto;
import com.example.tadak.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @RequestMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto requestDto) {
        return ResponseEntity
                .ok()
                .body(userService.createUser(requestDto));
    }
}
