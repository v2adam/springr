package com.springr.first.controller;


import com.springr.first.dto.RandomUser.RandomUserDTO;
import com.springr.first.exceptions.RandomUserException;
import com.springr.first.service.RandomUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my_api")
public class RandomUserController {


    Logger logger = LoggerFactory.getLogger(getClass());



    private RandomUserServiceImpl randomUserService;


    @Autowired
    public void setRandomUserService(@Qualifier("randomUserServiceImpl") RandomUserServiceImpl randomUserService) {
        this.randomUserService = randomUserService;
    }


    // Returns a list
    @RequestMapping(value = "random_users", method = RequestMethod.GET)
    public Iterable<RandomUserDTO> findAll() {

        logger.info(randomUserService.findAll().toString());

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
        try {
            randomUserService.delete(id);
        } catch (RandomUserException e) {
            System.out.println(e.toString());
        }

    }

}
