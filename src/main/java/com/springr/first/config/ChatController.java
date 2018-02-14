package com.springr.first.config;

import com.springr.first.misc.ChatMessage;
import com.springr.first.service.auth.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.Date;

import static com.springr.first.config.WebSocketConfig.MESSAGE_PREFIX;


@Slf4j
@Controller
public class ChatController {


    @MessageMapping("/newMessage")
    @SendTo(MESSAGE_PREFIX + "/newMessage")
    public ChatMessage messagePosted(ChatMessage msg) throws Exception {

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();

        msg.setAuthor(name);
        msg.setTimeStamp(new Date());

        return msg;
    }

}