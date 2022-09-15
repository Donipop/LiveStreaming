package com.donipop.livestreaming.model.service;

import com.donipop.livestreaming.model.dto.Session;
import com.donipop.livestreaming.model.dto.User;
import com.donipop.livestreaming.model.mapper.SessionMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class SessionService {

    private final SessionMapper sessionMapper;

    public SessionService(SessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    public void save(Session session){
        sessionMapper.save(session);
    }

    public Session findByValue(String u_key){
        return this.sessionMapper.findByValue(u_key);
    }

    public void update(Session session){this.sessionMapper.update(session);}

    public String createSession(){
        return UUID.randomUUID().toString();
    }

    public List<Session> findAll(){
        return sessionMapper.findAll();
    }
}
