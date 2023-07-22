package com.example.tadak.user.controller;

import com.example.tadak.user.data.LoginResponseDto;
import com.example.tadak.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tadak.user.data.SocialType.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
