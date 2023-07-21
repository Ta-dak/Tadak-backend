package com.example.tadak.auth.service;

import com.example.tadak.auth.data.GoogleResponseDto;
import com.example.tadak.user.data.LoginType;
import com.example.tadak.util.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
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

    public ResponseEntity<String> createGetRequest(String oAuthToken, LoginType loginType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + oAuthToken);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

            return restTemplate.exchange(loginType.getUserInfoUrl(), loginType.getMethod(), request, String.class);
        } catch (Exception e) {
            throw new CustomException(BAD_REQUEST_TOKEN_INVALID);
        }
    }

    public String getEmail(ResponseEntity<String> userInfoResponse) {
        try {
            GoogleResponseDto googleUser = objectMapper.readValue(userInfoResponse.getBody(), GoogleResponseDto.class);
            return googleUser.getEmail();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
