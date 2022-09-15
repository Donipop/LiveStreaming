package com.donipop.livestreaming.model.service;

import com.donipop.livestreaming.model.dto.User;
import com.donipop.livestreaming.model.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class LoginService {

    private final UserMapper userMapper;
    public LoginService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public void save(User user){
        userMapper.save(user);
    }
    public String login(User user){
        //로그인성공 1 실패 null 반환
        return userMapper.login(user);
    }
    public User findById(String id){
        return userMapper.findById(Integer.parseInt(id));
    }
    public User findByUserID(String userID){
        return userMapper.findByUserID(userID);
    }
}


