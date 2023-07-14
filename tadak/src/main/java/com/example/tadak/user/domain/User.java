package com.example.tadak.user.domain;

import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User extends Timestamped {
    private long id;
    private String nickname;
    private String email;
    private String loginType;


}
