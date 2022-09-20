package com.donipop.livestreaming.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.socket.WebSocketSession;


@Getter
@Setter
@ToString
public class ChatSocketSession{
    private WebSocketSession session;
    private String channelID;
    private String UserID;
    private String Username;

    public ChatSocketSession() {
    }

}

