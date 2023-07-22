package com.example.tadak.user.domain;

import com.example.tadak.user.data.SocialType;
import com.example.tadak.user.data.RoleType;
import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    private String id;
    private String nickname;
    private String socialId;
    private String socialType;
    private List<String> roles = new ArrayList<>();

    public User(String nickname, String socialId, SocialType socialType) {
        this.nickname = nickname;
        this.socialId = socialId;
        this.socialType = socialType.getSocialName();
        this.roles.add(RoleType.USER.getRole());
    }
}
