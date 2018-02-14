package com.springr.first.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.springr.first.config.WebSocketConfig.MESSAGE_PREFIX;


@Slf4j
@Controller
public class ChatController {

    @MessageMapping("/newMessage")
    @SendTo(MESSAGE_PREFIX + "/newMessage")
    public String messagePosted(String msg) throws Exception {
        return msg;
    }

}