package com.springr.first.controller;


import com.springr.first.dto.RandomUser.RandomUserDTO;
import com.springr.first.service.RandomUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import static com.springr.first.config.WebSocketConfig.MESSAGE_PREFIX;

@Slf4j
@RestController
@RequestMapping("/my_api")
public class RandomUserController {


    private RandomUserServiceImpl randomUserService;


    @Autowired
    public void setRandomUserService(@Qualifier("randomUserServiceImpl") RandomUserServiceImpl randomUserService) {
        this.randomUserService = randomUserService;
    }


    // Returns a list
    @MessageMapping("/find_again")
    @SendTo(MESSAGE_PREFIX + "/updated_list")
    @RequestMapping(value = "random_users", method = RequestMethod.GET)
    public Iterable<RandomUserDTO> findAll() {
        return randomUserService.findAll();
    }

    // Returns a specific
    @RequestMapping(value = "random_users/{id}", method = RequestMethod.GET)
    public RandomUserDTO findSpecific(@PathVariable("id") Long id) {
        return randomUserService.findOne(id);
    }

    // create a new
    @RequestMapping(value = "random_users", method = RequestMethod.POST)
    public RandomUserDTO createNew(@RequestBody RandomUserDTO randomUser) {
        return randomUserService.save(randomUser);
    }

    // Bulk update
    @RequestMapping(value = "random_users", method = RequestMethod.PUT)
    public Iterable<RandomUserDTO> update(@RequestBody Iterable<RandomUserDTO> randomUsers) {
        return randomUserService.bulkUpdate(randomUsers);
    }

    // Updates one
    @RequestMapping(value = "random_users/{id}", method = RequestMethod.PUT)
    public RandomUserDTO updateOne(@PathVariable("id") Long id, @RequestBody RandomUserDTO randomUserDTO) {
        return randomUserService.updateOne(id, randomUserDTO);
    }

    // Delete all
    @RequestMapping(value = "random_users", method = RequestMethod.DELETE)
    public void deleteAll() {
        randomUserService.deleteAll();
    }


    // Delete a specific
    @RequestMapping(value = "random_users/{id}", method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable("id") Long id) {
        randomUserService.delete(id);
    }

}
