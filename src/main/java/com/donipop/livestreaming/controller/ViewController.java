package com.donipop.livestreaming.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@Controller
public class ViewController {
    @GetMapping("view")
    public String getView(){
        return "/view";
    }

    @GetMapping("view/{channel}")
    public String get_channel_view(@PathVariable String channel){

        return "/view";
    }
}
