package com.donipop.livestreaming.controller;

import com.donipop.livestreaming.model.dto.User;
import com.donipop.livestreaming.model.service.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("login")
    public String loginGET(){
        return "login";
    }
    @PostMapping("login")
    public ModelAndView loginPOST(User user){
        log.info(user.getUser_id() + " 로그인 요청");
        //log.info(loginService.login(user));

        ModelAndView mv = new ModelAndView();
        if(loginService.login(user) == null){
            mv.setViewName("/login");
            mv.addObject("logindata","로그인 실패");
        }else{
            mv.setViewName("/login");
            mv.addObject("logindata","로그인 성공");
        }
        
        return mv;
    }

}
