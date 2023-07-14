package com.example.tadak.room.domain;

import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
public class Room extends Timestamped {
    @Id
    private long id;
    private long manager_id;
    private long notice_id;
    private String url;
}
