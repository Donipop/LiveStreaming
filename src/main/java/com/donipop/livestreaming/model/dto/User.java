package com.donipop.livestreaming.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String user_id;
    private String user_pw;
    private String user_nick;
}
