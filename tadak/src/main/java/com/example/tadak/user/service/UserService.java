package com.example.tadak.user.service;

import com.example.tadak.user.data.SignupRequestDto;
import com.example.tadak.user.domain.User;
import com.example.tadak.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 임시 MongoDB 테스트용 메소드
    public User createUser(SignupRequestDto requestDto) {
        return userRepository.save(new User(requestDto));
    }
}
