package com.springr.first.controller;


import com.springr.first.service.processXls.MyFirstRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my_api")
public class MyFirstRowController {


    private MyFirstRowService myFirstRowService;

    @Autowired
    public void setMyFirstRowService(@Qualifier("myFirstRowServiceImpl") MyFirstRowService myFirstRowService) {
        this.myFirstRowService = myFirstRowService;
    }

    // Delete a specific
    @RequestMapping(value = "my_first_xls/{id}", method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable("id") Long id) {
        myFirstRowService.delete(id);
    }


}
