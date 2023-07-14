package com.example.tadak.room.entity;

import com.example.tadak.chat.entity.Chat;
import com.example.tadak.user.entity.User;
import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Room extends Timestamped {
    long id;
    private User manager;
    private Chat notice;
    private String url;
}
