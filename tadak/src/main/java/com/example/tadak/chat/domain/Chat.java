package com.example.tadak.chat.domain;

import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
public class Chat extends Timestamped {
    @Id
    private long id;
    private long room_id;
    private String senderIp;
    private String content;
    private String imgUrl;
    private boolean isManager;
    private boolean isDeleted;
}
