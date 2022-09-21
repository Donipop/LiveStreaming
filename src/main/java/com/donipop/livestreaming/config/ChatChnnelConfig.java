package com.donipop.livestreaming.config;

import com.donipop.livestreaming.model.dto.ChatSocketSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.WebSocketSession;

import java.time.temporal.Temporal;
import java.util.*;

@Log4j2
public class ChatChnnelConfig {
    private HashMap<String,List<ChatSocketSession>> channel = new HashMap<>();
    private List<ChatSocketSession> channel_member_list =  new ArrayList<>();

    private HashMap<WebSocketSession,List<ChatSocketSession>> usersession = new HashMap<>();
    private List<ChatSocketSession> user_session_list = new ArrayList<>();
    public String channeljoin(ChatSocketSession session){
        usersession_add(session);
        channel_add(session);
        log.info(session.getChannelID() + "번 채널에 " + session + " 입장");
        return session.getChannelID() + "번 채널에 " + session + " 입장";
    }
    public void channelleave(WebSocketSession session){
        if(session != null)
            channel_member_list = usersession.get(session);
        for(int i=0; i<channel_member_list.size(); i++){
            ChatSocketSession temp = channel_member_list.get(i);
            if(temp != null && temp.getSession().equals(session)){
                usersession_del(session,temp.getChannelID());
                channel_del(temp);
            }
        }
    }
    public List<ChatSocketSession> channel_userList(String channelID){
        return channel.get(channelID);
    }
    public List<String> channelList(){
        return new ArrayList<>(channel.keySet());
    }
    private void usersession_add(ChatSocketSession chatSocketSession){
        if(usersession.containsKey(chatSocketSession.getSession())){
            //세션존재
            user_session_list = usersession.get(chatSocketSession.getSession());
            //세션,유저정보
            for(ChatSocketSession temp : user_session_list){
                if(temp.getChannelID().equals(chatSocketSession.getChannelID())){
                    user_session_list.remove(temp);
                }
            }
        }
        user_session_list.add(chatSocketSession);
        usersession.put(chatSocketSession.getSession(),user_session_list);
    }
    private void usersession_del(WebSocketSession session,String channelId){
            user_session_list = usersession.get(session);
            if(session != null){
                if(user_session_list != null){
                    for(int i =0; i<user_session_list.size();i++){
                        ChatSocketSession temp = user_session_list.get(i);
                        if(temp.getChannelID().equals(channelId)){
                            user_session_list.remove(temp);
                        }
                    }
                }
                usersession.put(session,user_session_list);
            }

    }
    private void channel_add(ChatSocketSession chatSocketSession){
        if(channel.containsKey(chatSocketSession.getChannelID())){
            channel_member_list = channel.get(chatSocketSession.getChannelID());
            for(ChatSocketSession temp : channel_member_list){
                if(temp.getSession().equals(chatSocketSession.getSession())){
                    channel_member_list.remove(temp);
                }
            }
            channel_member_list.add(chatSocketSession);
        }else{
            channel_member_list = new ArrayList<>();
            channel_member_list.add(chatSocketSession);
        }

        channel.put(chatSocketSession.getChannelID(),channel_member_list);
    }
    private void channel_del(ChatSocketSession chatSocketSession){
        if(channel.containsKey(chatSocketSession.getChannelID())){
            channel_member_list = channel.get(chatSocketSession.getChannelID());
            for(int i=0; i<channel_member_list.size(); i++){
                ChatSocketSession temp = channel_member_list.get(i);
                if(temp.getSession().equals(chatSocketSession.getSession())){
                    channel_member_list.remove(temp);
                }
            }
            channel.put(chatSocketSession.getChannelID(), channel_member_list);
        }
    }
}
