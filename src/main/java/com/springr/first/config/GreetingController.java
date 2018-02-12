package com.springr.first.config;

import com.springr.first.dto.RandomUser.RandomUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.springr.first.config.WebSocketConfig.MESSAGE_PREFIX;


@Slf4j
@Controller
public class GreetingController {


    @MessageMapping("/hello") // (a react erre a címre küldte)
    @SendTo(MESSAGE_PREFIX + "/greetings") // (aki erre feliratkozott, az megkapja)
    public String greeting(String message) throws Exception {
        log.info("Ezt kaptam: " + message);
        Thread.sleep(1000); // simulated delay
        return "ez a servertől jött";

    }

}