package com.springr.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// így lehet war-t csinálni a SpringBoot appból
// https://docs.spring.io/spring-boot/docs/current/reference/html/howto-traditional-deployment.html

/*
@SpringBootApplication
public class ReactAndSpringDataRestApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReactAndSpringDataRestApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReactAndSpringDataRestApplication.class, args);
    }

}
*/

@SpringBootApplication
public class ReactAndSpringDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactAndSpringDataRestApplication.class, args);
    }

}
