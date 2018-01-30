package com.springr.first.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean("myB")
    public String myBean() {
        return "This is my Bean";
    }

    @Bean("other")
    public String myOtherBean() {
        return "Other bean";
    }


    @Bean(destroyMethod = "", name = "myDs")
    @ConfigurationProperties(prefix = "my.app.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();

    }


}
