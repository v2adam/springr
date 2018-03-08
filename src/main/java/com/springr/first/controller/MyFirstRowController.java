package com.springr.first.controller;


import com.springr.first.service.processXls.MyFirstRowDTO;
import com.springr.first.service.processXls.MyFirstRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "my_first_xls", method = RequestMethod.POST)
    public MyFirstRowDTO createNew(@RequestBody MyFirstRowDTO myFirstRowDTO) {
        return myFirstRowService.save(myFirstRowDTO);
    }


    /*
        PATCH http://localhost:8080/addresses/1
                [
                    { "op": "replace", "path": "/fieldName1", "value": "new value is this" },
                    { "op": "remove", "path": "/otherFieldName" }
                ]
    */

    @RequestMapping(value = "my_first_xls/{id}", method = RequestMethod.PATCH, consumes = "application/json-patch+json")
    public ResponseEntity<?> patch(@PathVariable("id") Long id, @RequestBody List<Map<String, Object>> updates) {
        myFirstRowService.patch(id, updates);
        return ResponseEntity.ok("resource address updated");
    }


}
