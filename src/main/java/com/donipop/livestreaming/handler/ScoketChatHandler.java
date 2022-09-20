package com.donipop.livestreaming.handler;

import com.donipop.livestreaming.config.ChatChnnelConfig;
import com.donipop.livestreaming.model.dto.ChatSocketSession;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class ScoketChatHandler extends TextWebSocketHandler {
    private static List<WebSocketSession> list = new ArrayList<>();
    private ChatChnnelConfig chatChnnelConfig = new ChatChnnelConfig();
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        //log.info("payload : " + payload);
        JSONParser jsonParser = new JSONParser();
        JSONObject msg = (JSONObject) jsonParser.parse(payload);
        String type = String.valueOf(msg.get("type"));
        ChatSocketSession chatSocketSession = new ChatSocketSession();

        if(type.equals("join")){
            //log.info("쪼인");
            chatSocketSession.setSession(session);
            chatSocketSession.setChannelID(String.valueOf(msg.get("channelID")));
            chatSocketSession.setUserID(String.valueOf(msg.get("id")));
            chatSocketSession.setUsername(String.valueOf(msg.get("nick")));
            chatChnnelConfig.channeljoin(chatSocketSession);
        }else if(type.equals("message")){
            log.info("메시지");
        }else if(type.equals("userlist")){
            for(ChatSocketSession temp : chatChnnelConfig.channel_userList(String.valueOf(msg.get("channelID")))){
                log.info(temp.toString());
            }
        }

        //Object t = new Object();
        //log.info(msg.get("text"));
       /* for(WebSocketSession sess: list) {
            sess.sendMessage(new TextMessage(msg.toJSONString()));
            //sess.sendMessage(message);
        }*/
    }
    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        //list.add(session);

        //log.info(session + " 클라이언트 접속 : " + session.getUri());
        //chatChnnelConfig.channeljoin()
        //log.info(session);
    }
    /* Client가 접속 해제 시 호출되는 메서드드 */

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        log.info(session + " 클라이언트 접속 해제");
        list.remove(session);
        log.info(status);

        //chatChnnelConfig.channelout(session);
    }

}
