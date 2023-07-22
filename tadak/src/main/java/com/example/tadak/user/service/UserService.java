package com.example.tadak.user.service;

import com.example.tadak.auth.JwtTokenProvider;
import com.example.tadak.auth.service.OAuthService;
import com.example.tadak.user.data.LoginResponseDto;
import com.example.tadak.user.data.LoginType;
import com.example.tadak.user.domain.User;
import com.example.tadak.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final OAuthService oAuthService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final NicknameGenerator nicknameGenerator;

    @Transactional
    public LoginResponseDto oauthLogin(String token, LoginType loginType) {
        ResponseEntity<String> userInfoResponse = oAuthService.createGetRequest(token, loginType);
        String email = oAuthService.getEmail(userInfoResponse);

        User user = userRepository.findByEmail(email).orElse(new User(nicknameGenerator.generate(), email, loginType));
        userRepository.save(user);

        return new LoginResponseDto(
                user.getNickname(),
                user.getEmail(),
                user.getLoginType(),
                jwtTokenProvider.createToken(user));
    }
}
