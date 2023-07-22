package com.example.tadak.auth.service;

import com.example.tadak.auth.data.GoogleResponseDto;
import com.example.tadak.auth.data.KakakoResponseDto;
import com.example.tadak.user.data.SocialType;
import com.example.tadak.util.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.example.tadak.util.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> createSocialLoginRequest(String oAuthToken, SocialType socialType) {
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

    public String getUserSocialId(ResponseEntity<String> userInfoResponse, SocialType socialType) {
        try {
            if (SocialType.KAKAO.equals(socialType)) {
                KakakoResponseDto kakaoUser = objectMapper.readValue(userInfoResponse.getBody(), KakakoResponseDto.class);
                return kakaoUser.getId();
            } else if (SocialType.GOOGLE.equals(socialType)) {
                GoogleResponseDto googleUser = objectMapper.readValue(userInfoResponse.getBody(), GoogleResponseDto.class);
                return googleUser.getEmail();
            }
            throw new CustomException(BAD_REQUEST_INVALID_LOGIN_TYPE);
        } catch (JsonProcessingException e) {
            throw new CustomException(FORBIDDEN_TOKEN_NOT_VALID);
        }
    }
}
