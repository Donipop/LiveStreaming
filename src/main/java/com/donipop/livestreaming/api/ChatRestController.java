package com.donipop.livestreaming.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRestController {
    @GetMapping("/api/chat")
    public String chat(){
        return "chat";
    }
}
