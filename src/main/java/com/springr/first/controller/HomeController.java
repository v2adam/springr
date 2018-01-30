package com.springr.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "react_page";
    }

    @RequestMapping(value = "/react/**")
    public String reactPage() {
        return "react_page";
    }


    @RequestMapping(value = "/index2")
    public String index2() {
        System.out.println("/index2");
        return "index2";
    }


    @RequestMapping("/valami/{userId}")
    public String hello(Model model, @PathVariable("userId") int id, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        model.addAttribute("userId", id);
        return "valami";
    }

}
