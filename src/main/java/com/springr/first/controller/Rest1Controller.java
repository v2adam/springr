package com.springr.first.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// adatokat ad vissza
@RestController
public class Rest1Controller {

    private String myBean;

    @Autowired
    public void setMyBean(@Qualifier("other") String myBean) {
        this.myBean = myBean;
    }

    // így lehet levédeni a metódust
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/1")
    public String index() {
        return "1" + myBean;
    }

    @RequestMapping(value = "/2/{id}", method = RequestMethod.GET)
    public String index2(@PathVariable("id") String id) {
        return "2" + id;
    }

}
