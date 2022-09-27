package com.donipop.livestreaming.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.socket.WebSocketSession;


@Getter
@Setter
public class ChatSocketSession{
    private WebSocketSession session;
    private String channelID;
    private String UserID;
    private String Username;

    @Override
    public String toString() {
        return "{session=" + session.getId() +
                ", channelID='" + channelID + '\'' +
                ", UserID='" + UserID + '\'' +
                ", Username='" + Username + '\'' +
                '}';
    }
}

