package com.springr.first.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Slf4j
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
        registry.addEndpoint("/my_endpoint")./*setHandshakeHandler(new UsernameHandshakeHandler()).*/withSockJS();
    }


    // kb így lehetne belenyúlni
    private class UsernameHandshakeHandler extends DefaultHandshakeHandler {
        @Override
        protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
            Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new UsernamePasswordAuthenticationToken(principal, null);
        }
    }


}