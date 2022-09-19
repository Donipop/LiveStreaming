package com.donipop.livestreaming.config;

import com.donipop.livestreaming.model.dto.ChatSocketSession;
import com.donipop.livestreaming.model.dto.Session;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.WebSocketSession;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Log4j2
public class ChatChnnelConfig {
    private HashMap<String,List<ChatSocketSession>> channel = new HashMap<String,List<ChatSocketSession>>();
    //channel<channelid,list<chatsocketsession>)
    private List<ChatSocketSession> memberList =  new ArrayList<>();

    public String channeljoin(ChatSocketSession session){
        /*if(channel.containsKey(channelID)){
            memberList = channel.get(channelID);
            memberList.add(session);
            channel.put(channelID,memberList);
        }else if(!channel.containsKey(channelID)){
                log.info("username : null2!!");
                memberList.add(session);
                channel.put(channelID,memberList);
            }*/
        if(channel.containsKey(session.getChannelID())){
            //이미 채널이 존재하면
            memberList = channel.get(session.getChannelID());
        }
        memberList.add(session);
        channel.put(session.getChannelID(),memberList);
        log.info(session.getChannelID() + "번 채널에 " + session + " 입장");
        return session.getChannelID() + "번 채널에 " + session + " 입장";
    }

    /*public String channelout(String channelID,String username){
        if(channel.containsKey(channelID)){
            memberList = channel.get(channelID);
            memberList.remove(username);
            channel.put(channelID,memberList);
        }
        return channelID + "번 채널에서 " +  username + " 퇴장";
    }*/

    public List<ChatSocketSession> channel_userList(String channelID){
        memberList = channel.get(channelID);
        return memberList;
    }

    public List<String> channelList(){
        List<String> keyList = new ArrayList<>(channel.keySet());
        return keyList;
    }
}
