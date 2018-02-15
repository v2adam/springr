package com.springr.first.config;

import com.springr.first.misc.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.Date;


@Slf4j
@Configuration
public class WebSocketApplicationEventsDisconnected implements ApplicationListener<SessionDisconnectEvent> {

    private ChatController chatController;

    @Autowired
    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }


    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
       // StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

        Principal principal = event.getUser();
        log.info("disconnected: " + principal.getName());

        ChatMessage msg = new ChatMessage();
        msg.setTimeStamp(new Date());
        msg.setAuthor("System");
        msg.setContent("Disconnected: " + principal.getName());

        chatController.sendForAllUser(msg);

    }

}
