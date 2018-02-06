package com.springr.first.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Singleton;

@Configuration
@Singleton
public class JacksonConfig {

    @Bean("myObjectMapper")
    public ObjectMapper jackson() {
        return new ObjectMapper();
    }


}
