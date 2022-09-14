package com.donipop.livestreaming.handler;

import com.donipop.livestreaming.model.dto.Session;
import com.donipop.livestreaming.model.service.SessionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class LoginIntercepter implements HandlerInterceptor {


    private SessionService sessionService;
    //controller에 넘기기전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String loginSession = (String) request.getSession().getAttribute("loginID");

        //System.out.println(loginSession);
        if (loginSession != null){
            log.info("세션있음");
            log.info(loginSession);
            //세션있음
            Session ss = sessionService.findByValue(loginSession);
            log.info("여기도 나오나?");
            //ss = sessionService.findByValue(loginSession);
            System.out.println(ss.getU_value());
            //ss.setU_key(sessionService.createSession());
            //sessionService.update(ss);
            return true;
        }else{
            log.info("세션없음");
            //세션없음

        }

        return true;
    }
    //controller가 처리를 마친후
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }
    //view가 처리를 마친후
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }
}
