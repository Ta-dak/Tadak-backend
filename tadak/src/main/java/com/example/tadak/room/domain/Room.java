package com.example.tadak.room.domain;

import com.example.tadak.chat.domain.Chat;
import com.example.tadak.room.data.RoomRequestDto;
import com.example.tadak.user.domain.User;
import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@NoArgsConstructor
public class Room extends Timestamped {
    @Id
    private String id;
    private String title;
    private String managerId;
    private String noticeId;

    public Room(RoomRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.managerId = user.getId();
        this.noticeId = null;
    }
}
