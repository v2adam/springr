package com.springr.first.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker // turns on WebSocket support
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer { // provides a convenient base class to configure basic features.

    public static final String MESSAGE_PREFIX = "/topic";


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(MESSAGE_PREFIX, "/valami_mas"); // server -> client iránynak a prefix-e
        registry.setApplicationDestinationPrefixes("/app"); // client -> server iránynak a prefix-e, ezeket beleirányítja a controllerekbe
    }

    // STOMP endpoint,
    // a HandshakeHandler-rel lehet belenyúlni az egyetlen HTTP üzibe, mielőtt létrejön a connection
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/my_endpoint").withSockJS();
    }


}