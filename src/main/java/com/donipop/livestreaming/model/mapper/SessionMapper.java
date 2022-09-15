package com.donipop.livestreaming.model.mapper;

import com.donipop.livestreaming.model.dto.Session;
import com.donipop.livestreaming.model.dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//로그인 유지를 위해 세션을 데이터베이스에 저장
@Mapper
public interface SessionMapper {
    @Select("select * from session where u_key = #{u_key}")
    Session findByValue(@Param("u_key") String u_key);

    @Insert("insert into session (u_key, u_value) values (#{u_key}, #{u_value})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void save(Session session);

    @Update("update session set u_key = #{u_key}, u_value = #{u_value} where id = #{id}")
    void update(Session session);

    @Select("select * from session")
    List<Session> findAll();
}
