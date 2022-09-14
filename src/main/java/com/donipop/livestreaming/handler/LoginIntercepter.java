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
    @Autowired
    private SessionService sessionService;

    //controller에 넘기기전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String u_key = (String) request.getSession().getAttribute("loginID");

        Session ss = new Session();
        ss = sessionService.findByValue(u_key);
        log.info(ss.getU_value());


        if(u_key != null){
            //세션있음
        }else{
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
