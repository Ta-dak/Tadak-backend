package com.example.tadak.user.controller;

import com.example.tadak.user.data.SocialType;
import com.example.tadak.user.service.UserService;
import com.example.tadak.util.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tadak.util.ResponseCode.OK_SUCCESS;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping
    @RequestMapping("/{social-type}")
    public ResponseEntity<?> socialLogin(@RequestHeader("Authorization") String token,
                                         @PathVariable("social-type") String socialName) {
        userService.oauthLogin(token, SocialType.getSocialTypeBySocialName(socialName));
        return ResponseTemplate.toResponseEntity(OK_SUCCESS);
    }
}
