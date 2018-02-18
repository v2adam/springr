package com.springr.first.controller;

import com.springr.first.misc.ChatMessage;
import com.springr.first.service.auth.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.stream.Collectors;

import static com.springr.first.config.WebSocketConfig.MESSAGE_PREFIX;


@Slf4j
@Controller
public class ChatController {


    private final SimpMessagingTemplate websocket;

    @Autowired
    public ChatController(SimpMessagingTemplate websocket) {
        this.websocket = websocket;
    }


    private SimpUserRegistry simpUserRegistry;

    @Autowired
    public void setSimpUserRegistry(SimpUserRegistry simpUserRegistry) {
        this.simpUserRegistry = simpUserRegistry;
    }


    // közvetlenül küld
    public void sendForAllUser(ChatMessage msg) {
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/newMessage", msg);
    }


    @MessageMapping("/newMessage")
    @SendTo(MESSAGE_PREFIX + "/newMessage")
    public ChatMessage messagePosted(ChatMessage msg) {

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();

        msg.setAuthor(name);
        msg.setTimeStamp(new Date());

        return msg;
    }

    @MessageMapping("/allUsers")
    public void allUsers() {
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/allUsers", simpUserRegistry.getUsers().stream().map(p -> p.getName()).collect(Collectors.toList()));
    }

}