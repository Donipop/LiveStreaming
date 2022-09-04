package com.donipop.livestreaming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChatController{
    @RequestMapping("/chat")
    public ModelAndView chat(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chat"); //연결될 html
        return mv;
    }
}
