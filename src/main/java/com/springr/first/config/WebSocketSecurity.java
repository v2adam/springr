package com.springr.first.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurity extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                // security állítás a kliensektől jövő info-ra
                // .simpMessageDestMatchers("/topic/chat.login", "/topic/chat.logout", "/topic/chat.message").denyAll()
                // .simpMessageDestMatchers("/user/**").hasAuthority("ADMIN")
                .anyMessage().authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        //disable CSRF for websockets for now...
        return true;
    }

}