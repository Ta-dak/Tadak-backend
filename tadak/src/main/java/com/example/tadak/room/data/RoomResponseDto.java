package com.example.tadak.room.data;

import com.example.tadak.room.domain.Room;
import lombok.Data;

@Data
public class RoomResponseDto {
    private String id;
    private String title;

    public RoomResponseDto(Room room) {
        this.id = room.getId();
        this.title = room.getTitle();
    }
}
