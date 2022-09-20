package com.donipop.livestreaming.config;

import com.donipop.livestreaming.model.dto.ChatSocketSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Log4j2
public class ChatChnnelConfig {
    private HashMap<String,List<ChatSocketSession>> channel = new HashMap<>();
    private HashMap<WebSocketSession,List<ChatSocketSession>> usersession = new HashMap<>();
    private List<ChatSocketSession> channel_member_list =  new ArrayList<>();
    private List<ChatSocketSession> user_session_list = new ArrayList<>();
    public String channeljoin(ChatSocketSession session){
        log.info("세션넘어옴 " + session);

        if(usersession.containsKey(session.getSession())){
            //유저세션에 테이블이 있으면
            user_session_list = usersession.get(session.getSession());
            user_session_list.add(session);
        }else{
            user_session_list.add(session);
            usersession.put(session.getSession(), user_session_list);
        }



        if(channel.containsKey(session.getChannelID())){
            //이미 채널이 존재하면
            channel_member_list = channel.get(session.getChannelID());
            channel_member_list.add(session);
        }else {
            channel_member_list.add(session);
            channel.put(session.getChannelID(), channel_member_list);
        }


        //log.info(session.getChannelID() + "번 채널에 " + session + " 입장");
        return session.getChannelID() + "번 채널에 " + session + " 입장";
    }
    private void usersession_add(ChatSocketSession chatSocketSession){
        if(usersession.containsKey(chatSocketSession.getSession())){
            for(ChatSocketSession temp : user_session_list){

            }
        }else{
            user_session_list.add(chatSocketSession);
            usersession.put(chatSocketSession.getSession(),user_session_list);
        }
    }
    public String channelout(WebSocketSession session){
        if(usersession.containsKey(session)){
            //테이블에 세션이 있으면
            user_session_list = usersession.get(session);
            for(ChatSocketSession temp : user_session_list){

            }
            usersession.remove(session);
        }
        return "";//session.getChannelID() + "번 채널에서 " +  session.getUsername() + " 퇴장";
    }
    public List<ChatSocketSession> channel_userList(String channelID){
        channel_member_list = channel.get(channelID);
        return channel_member_list;
    }
    public List<String> channelList(){
        List<String> keyList = new ArrayList<>(channel.keySet());
        return keyList;
    }
}
