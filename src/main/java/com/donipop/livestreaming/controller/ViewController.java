package com.donipop.livestreaming.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class ViewController {
    @GetMapping("view")
    public String get_view(){
        return "view";
    }
}
