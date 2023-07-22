package com.example.tadak.user.service;

import com.example.tadak.auth.JwtTokenProvider;
import com.example.tadak.auth.service.OAuthService;
import com.example.tadak.user.data.LoginResponseDto;
import com.example.tadak.user.data.SocialType;
import com.example.tadak.user.domain.User;
import com.example.tadak.user.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

}
