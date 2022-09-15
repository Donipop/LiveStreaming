package com.donipop.livestreaming.handler;

import com.donipop.livestreaming.model.dto.Session;
import com.donipop.livestreaming.model.dto.User;
import com.donipop.livestreaming.model.service.LoginService;
import com.donipop.livestreaming.model.service.SessionService;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class LoginIntercepter implements HandlerInterceptor {
    private final SessionService sessionService;

    private final LoginService loginService;

    public LoginIntercepter(SessionService sessionService, LoginService loginService) {
        this.sessionService = sessionService;
        this.loginService = loginService;
    }


    //controller에 넘기기전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String loginSession = (String) request.getSession().getAttribute("loginID");
        if (loginSession != null){
            //세션있음
            Session ss = sessionService.findByValue(loginSession);
            if (ss != null){
                //세션이 있고 정상적인 세션일때
                log.info("정상 세션");
                //새로운 uuid key발급
                String uuid = sessionService.createSession();
                request.getSession().setAttribute("loginID",uuid);
                ss.setU_key(uuid);
                sessionService.update(ss);
            }else{
                //세션은 존재하지만 비정상적인 세션일때
                log.info("비정상 세션");
                //세션초기화
                request.setAttribute("loginID","");
            }
        }else{
            log.info("세션없음");
            //세션없음
        }
        return true;
    }
    //controller가 처리를 마친후 view까진 아직 도달 안함
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        User user;
        if(request.getSession().getAttribute("loginID") != null){
            user = loginService.findByUserID(sessionService.findByValue((String) request.getSession().getAttribute("loginID")).getU_value());
            user.setUser_pw("*");
            modelAndView.addObject("UserIn", user);
        }

    }
    //view가 처리를 마친후
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {


    }
}
