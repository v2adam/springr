package com.springr.first.config;


import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Component
@EnableWebSocketMessageBroker // turns on WebSocket support
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer { // provides a convenient base class to configure basic features.

    public static final String MESSAGE_PREFIX = "/topic";


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(MESSAGE_PREFIX); // server -> client iránynak a prefix-e
        registry.setApplicationDestinationPrefixes("/app"); // client -> server iránynak a prefix-e
    }

    // a kliensnek erre kell bejelentkezni, ha akar valamit
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/my_endpoint").withSockJS();
    }


}