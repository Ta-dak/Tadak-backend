package com.example.tadak.chat.domain;

import com.example.tadak.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
public class Chat extends Timestamped {
    @Id
    private String id;
    private String roomId;
    private String senderIp;
    private String content;
    private String imgUrl;
    private boolean isManager;
    private boolean isDeleted;
}
