package com.example.tadak.auth.service;

import com.example.tadak.auth.JwtTokenProvider;
import com.example.tadak.auth.data.GoogleProperty;
import com.example.tadak.auth.data.GoogleUserInfo;
import com.example.tadak.user.data.LoginResponseDto;
import com.example.tadak.user.data.SocialType;
import com.example.tadak.user.domain.User;
import com.example.tadak.user.repository.UserRepository;
import com.example.tadak.user.service.NicknameGenerator;
import com.example.tadak.util.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.example.tadak.util.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final NicknameGenerator nicknameGenerator;
    private final GoogleProperty googleProperty;

    @Transactional
    public LoginResponseDto oauthLogin(String token, SocialType socialType) {
        ResponseEntity<String> userInfoResponse = createGetRequest(token, socialType);
        var userInfo = getGoogleUserInfo(userInfoResponse);

        User user = userRepository.findBySocialIdAndSocialType(userInfo.getSub(), socialType.getSocialName())
                .orElse(new User(nicknameGenerator.generate(), userInfo.getSub(), socialType));
        userRepository.save(user);

        return new LoginResponseDto(
                user.getNickname(),
                user.getSocialId(),
                user.getSocialType(),
                jwtTokenProvider.createToken(user));
    }

    public void getSocialAccessToken(String code, String registrationId) {
        if (registrationId.equals("google"))
            System.out.println(getGoogleAccessToken(code));
    }

    private String getGoogleAccessToken(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", googleProperty.getClientId());
        params.put("client_secret", googleProperty.getClientSecret());
        params.put("redirect_uri", googleProperty.getRedirectUri());
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(googleProperty.getTokenUri(), params, String.class);
        return responseEntity.getBody();
    }

    private ResponseEntity<String> createGetRequest(String oAuthToken, SocialType socialType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + oAuthToken);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

            return restTemplate.exchange(socialType.getUserInfoUrl(), socialType.getMethod(), request, String.class);
        } catch (Exception e) {
            throw new CustomException(BAD_REQUEST_TOKEN_INVALID);
        }
    }

    private GoogleUserInfo getGoogleUserInfo(ResponseEntity<String> userInfoResponse) {
        try {
            return objectMapper.readValue(userInfoResponse.getBody(), GoogleUserInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
