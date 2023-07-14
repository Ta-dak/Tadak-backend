package com.example.tadak.chat.entity;

import com.example.tadak.room.entity.Room;
import com.example.tadak.util.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Chat extends Timestamped {
    private long id;
    private Room room;
    private String senderIp;
    private String content;
    private String imgUrl;
    private boolean isManager;
    private boolean isDeleted;
}
