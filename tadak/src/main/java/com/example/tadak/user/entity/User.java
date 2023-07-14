package com.example.tadak.user.entity;

import com.example.tadak.user.data.LoginType;
import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User extends Timestamped {
    private long id;

    private String nickname;

    private String email;

    private LoginType loginType;


}
