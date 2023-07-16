package com.example.tadak.auth;

import com.example.tadak.user.domain.User;
import com.example.tadak.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user =  userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("해당 ID의 사용자가 없습니다."));
        return new CustomUserDetails(user);
    }
}
