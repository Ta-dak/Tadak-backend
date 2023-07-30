package com.example.tadak.auth.service;

import com.example.tadak.auth.JwtTokenProvider;
import com.example.tadak.auth.data.GoogleUserDto;
import com.example.tadak.auth.data.KakaoUserDto;
import com.example.tadak.auth.data.TwitterUserDto;
import com.example.tadak.user.data.LoginResponseDto;
import com.example.tadak.user.data.SocialType;
import com.example.tadak.user.domain.User;
import com.example.tadak.user.repository.UserRepository;
import com.example.tadak.user.service.NicknameGenerator;
import com.example.tadak.util.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.example.tadak.user.data.SocialType.*;
import static com.example.tadak.util.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final NicknameGenerator nicknameGenerator;


    @Transactional
    public LoginResponseDto oauthLogin(String oAuthToken, SocialType socialType) {
        ResponseEntity<String> response = sendUserInfoRequest(oAuthToken, socialType);
        String socialId = getSocialId(response, socialType);

        User user = userRepository.findBySocialIdAndSocialType(socialId, socialType.getSocialName())
                .orElse(new User(nicknameGenerator.generate(), socialId, socialType));
        userRepository.save(user);

        return new LoginResponseDto(
                user.getNickname(),
                jwtTokenProvider.createToken(user));
    }

    private ResponseEntity<String> sendUserInfoRequest(String oAuthToken, SocialType socialType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + oAuthToken);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

            return restTemplate.exchange(socialType.getUserInfoUrl(), socialType.getMethod(), request, String.class);
        } catch (Exception e) {
            throw new CustomException(SERVER_ERROR_CONNECTION);
        }
    }

    private String getSocialId(ResponseEntity<String> response, SocialType socialType) {
        try {
            if (socialType.equals(KAKAO)) {
                KakaoUserDto kakaoUser = objectMapper.readValue(response.getBody(), KakaoUserDto.class);
                return kakaoUser.getId();
            }

            if (socialType.equals(GOOGLE)) {
                GoogleUserDto googleUser = objectMapper.readValue(response.getBody(), GoogleUserDto.class);
                return googleUser.getSub();
            }

            TwitterUserDto twitterUser = objectMapper.readValue(response.getBody(), TwitterUserDto.class);
            return twitterUser.getId();

        } catch (JsonProcessingException e) {
            throw new CustomException(FORBIDDEN_TOKEN_NOT_VALID);
        }
    }
}
