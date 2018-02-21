package com.springr.first.controller;

import com.springr.first.misc.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.util.Date;


@Slf4j
@Controller
public class WebSocketApplicationEventsConnected implements ApplicationListener<SessionConnectedEvent> {

    private ChatController chatController;

    @Autowired
    public void setChatController(ChatController chatController) {
        this.chatController = chatController;
    }


    /*
        SessionUnsubscribeEvent
        SessionConnectEvent
        SessionConnectedEvent
        SessionSubscribeEvent
        SessionDisconnectEvent
    */


    private SimpUserRegistry simpUserRegistry;

    @Autowired
    public void setSimpUserRegistry(SimpUserRegistry simpUserRegistry) {
        this.simpUserRegistry = simpUserRegistry;
    }


    // így lehet megfogni az üziket, és kiszedni belőlük azt amit kell
    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        //StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());

        Principal principal = event.getUser();
        log.info("connected: " + principal.getName());
        log.info("connectedUsers: " + simpUserRegistry.getUsers());
        chatController.allUsers();
        chatController.sendForAllUser(ChatMessage.builder().author("System").timeStamp(new Date()).content("Connected: " + principal.getName()).build());
    }
}