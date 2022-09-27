package com.donipop.livestreaming.api;



import com.donipop.livestreaming.model.service.ChatChnnelService;
import com.donipop.livestreaming.model.dto.ChatSocketSession;
import lombok.extern.log4j.Log4j2;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@RestController
public class ChatRestController {
    ChatChnnelService chatChnnelService = ChatChnnelService.getInstance();
    @GetMapping("/api/chat")
    public String chat(){
        return "chat";
    }
    @GetMapping("/api/chat/list/{channel}")
    public List<Object> channel_user_list(@PathVariable String channel){
        if(chatChnnelService.channel_userList(channel) == null)
            return null;
        List<Object> userList = new ArrayList<>();
         for(ChatSocketSession temp : chatChnnelService.channel_userList(channel)){
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("id",temp.getUserID());
             jsonObject.put("name",temp.getUsername());
                userList.add(jsonObject);
            }
        return userList;
    }
    @GetMapping("/api/chat/list")
    public List<String> channelList(){
        return chatChnnelService.channelList();
    }
}
