package com.example.tadak.user.domain;

import com.example.tadak.user.data.LoginType;
import com.example.tadak.user.data.RoleType;
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

    public User(String nickname, String email, LoginType loginType) {
        this.nickname = nickname;
        this.email = email;
        this.loginType = loginType.getSocialName();
        this.roles.add(RoleType.USER.getRole());
    }
}
