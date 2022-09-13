package com.donipop.livestreaming.model.mapper;

import com.donipop.livestreaming.model.dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    User findById(int id);

    @Insert("insert into user (user_id, user_pw, user_nick) values (#{user_id}, #{user_pw}, #{user_nick})")
    @Options(useGeneratedKeys=true, keyProperty = "id")
    void save(User user);

    @Select("select * from user where user_id=#{user_id} and user_pw=#{user_pw}")
    String login(User user);

}
