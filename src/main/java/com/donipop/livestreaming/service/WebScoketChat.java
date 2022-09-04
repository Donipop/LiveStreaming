package com.donipop.livestreaming.service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Service
@ServerEndpoint(value = "/chat")
public class WebScoketChat {
    //클라이언트가 접속할때마다 세션정보 저장하여 1:N통신이 가능하도록 만듦
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

//    클라이언트 접속
    @OnOpen
    public void onOpen(Session session){
        log.info("open : " + session.toString());
        if(!clients.contains(session)){
            clients.add(session);
            log.info("set client : " + session);
        }else{
            log.info("이미 연결된 세션");
        }
    }
//    클라이언트 종료
    @OnClose
    public void onClose(Session session){
        log.info("close : " + session);
    }
//    클라이언트 메시지 수신
    @OnMessage
    public void onMessage(String msg, Session session) throws IOException {
        log.info("받은 메시지 : " + msg);
        for(Session s : clients){
            log.info("send data : " + msg);
            s.getBasicRemote().sendText(msg);
        }
    }
}
