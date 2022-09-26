package com.donipop.livestreaming.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@RestController
@Controller
@Log4j2
public class ChatController{
    //@RequestMapping("/chat")
    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }
    @GetMapping("/chat/{channel}")
    public String chats(@PathVariable String channel){
        return "/chat";
    }
}
