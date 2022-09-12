package com.donipop.livestreaming.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@RestController
@Controller
public class ChatController{
    //@RequestMapping("/chat")
    @GetMapping("/chat")
    public String chat(){
        /*ModelAndView mv = new ModelAndView();
        mv.setViewName("chat"); //연결될 html
        return mv;*/
        //System.out.println("@Chat");
        return "chat";
    }
}
