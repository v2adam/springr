package com.springr.first.controller;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;

//@Ignore
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class HomeControllerTest {

/*
    @Autowired
    private HomeController homeController;


    @Before
    public void setup() throws AuthenticationException {

    }

    @Test
    public void testIndex() {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("REQUESTED_URL", "/");
        String view = homeController.index();
        assertEquals("react_page", view);
    }


    @Configuration
    static class LoginControllerTestConfiguration {

        @Bean
        public HomeController homeController() {
            return new HomeController();
        }
    }

*/
}
