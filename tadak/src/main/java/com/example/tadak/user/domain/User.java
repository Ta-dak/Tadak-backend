package com.example.tadak.user.domain;

import com.example.tadak.user.data.SignupRequestDto;
import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    private String id;
    private String nickname;
    private String email;
    private String loginType;
    private List<String> roles = new ArrayList<>();

    public User(SignupRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.email = requestDto.getEmail();
        this.loginType = requestDto.getLoginType();
        this.roles.add("USER");
    }
}
