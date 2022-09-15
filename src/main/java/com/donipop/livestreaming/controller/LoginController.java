package com.donipop.livestreaming.controller;

import com.donipop.livestreaming.model.dto.Session;
import com.donipop.livestreaming.model.dto.User;
import com.donipop.livestreaming.model.service.LoginService;
import com.donipop.livestreaming.model.service.SessionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("login")
    public String loginGET(){
        //log.info();
        return "login";
    }
    @PostMapping("login")
    public ModelAndView loginPOST(User user, HttpSession session){
        log.info(user.getUser_id() + " 로그인 요청");
        ModelAndView mv = new ModelAndView();

        if(loginService.login(user) == null){
            mv.setViewName("/login");
            mv.addObject("logindata","로그인 실패");
        }else{
            String uuid = sessionService.createSession();
            Session ss = new Session();
            ss.setU_key(uuid);
            ss.setU_value(user.getUser_id());
            sessionService.save(ss);

            session.setAttribute("loginID",uuid);
            mv.setViewName("/login");
            mv.addObject("logindata","로그인 성공");
        }
        
        return mv;
    }

}
