package com.springr.first.service.auth;


import com.springr.first.domain.User;

public interface UserService {

    String registerUser(User user);

    User findByUserName(String userName);

    void deleteUser(User user);

}