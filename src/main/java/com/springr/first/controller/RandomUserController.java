package com.springr.first.controller;


import com.springr.first.domain.RandomUser;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import com.springr.first.misc.DTO;
import com.springr.first.service.RandomUserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/my_api")
public class RandomUserController {


    private RandomUserServiceImpl randomUserService;


    @Autowired
    public void setRandomUserService(@Qualifier("randomUserServiceImpl") RandomUserServiceImpl randomUserService) {
        this.randomUserService = randomUserService;
    }


    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(@Qualifier("myModelMapper") ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private RandomUserDTO convertToDTO(RandomUser randomUser) {
        return modelMapper.map(randomUser, RandomUserDTO.class);
    }

    private RandomUser convertToEntity(RandomUserDTO randomUserDTO) {
        return modelMapper.map(randomUserDTO, RandomUser.class);
    }


    // Returns a list
    @RequestMapping(value = "random_users", method = RequestMethod.GET)
    public Iterable<RandomUserDTO> getAllRandomUserDTOs() {
        List<RandomUser> randomUserList = (List<RandomUser>) randomUserService.findAll();
        return randomUserList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    // Returns a specific
    @RequestMapping(value = "random_users/{id}", method = RequestMethod.GET)
    public RandomUserDTO findOne(@PathVariable("id") Long id) {
        return convertToDTO(randomUserService.findOne(id));
    }

/*
    // Create a new
    @RequestMapping(value = "random_users", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public RandomUserDTO createNew(@RequestBody RandomUserDTO inputRandomUserDTO) {
        RandomUser randomUser = convertToEntity(inputRandomUserDTO);
        RandomUser created = randomUserService.save(randomUser);
        return convertToDTO(created);
    }
*/
/*
    @RequestMapping(value = "random_users", method = RequestMethod.POST)
    public void newExam(@RequestBody RandomUserDTO randomUserDTO) {

        System.out.println(randomUserDTO.toString());

        randomUserService.save(convertToEntity(randomUserDTO));
    }
*/

    @RequestMapping(value = "random_users", method = RequestMethod.POST)
    public void newExam(@DTO(RandomUserDTO.class) RandomUser randomUser ) {

        System.out.println(randomUser.toString());

        randomUserService.save(randomUser);
    }



    // Bulk update
    @RequestMapping(value = "random_users", method = RequestMethod.PUT)
    public Iterable<RandomUserDTO> update(@RequestBody Iterable<RandomUserDTO> randomUsers) {

        List<RandomUser> randomUserList = new ArrayList<>();

        randomUsers.forEach(
                p -> {
                    randomUserList.add(convertToEntity(p));
                }
        );

        List<RandomUser> updated = (List<RandomUser>) randomUserService.save(randomUserList);

        return updated.stream().map(p -> convertToDTO(p)).collect(Collectors.toList());
    }


    // Updates one
    @RequestMapping(value = "random_users/{id}", method = RequestMethod.PUT)
    public RandomUserDTO updateOne(@PathVariable("id") Long id, @RequestBody RandomUserDTO randomUserDTO) {
        RandomUser randomUser = convertToEntity(randomUserDTO);
        return convertToDTO(randomUserService.update(id, randomUser));
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
