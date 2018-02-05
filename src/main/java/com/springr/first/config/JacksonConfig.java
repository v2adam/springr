package com.springr.first.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {


    @Bean("myJackson")
    public ObjectMapper jackson() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }


}
