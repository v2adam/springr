package com.springr.first.service;

import com.springr.first.domain.Role;
import com.springr.first.domain.User;
import com.springr.first.repo.RoleRepository;
import com.springr.first.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoServiceImpl {


    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    private RoleRepository roleRepository;


    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Iterable<User> selectNative(String userName) {
        return userRepository.selectNative(userName);
    }


    @Transactional
    public Iterable<User> select(User user) {
        return userRepository.select(user);
    }

    @Transactional
    public Integer updateNative(String userName) {
        return userRepository.updateNative(userName);
    }

    @Transactional
    public Integer update(User user) {
        return userRepository.update(user);
    }

    @Transactional
    public void deleteC(User user) {
        userRepository.deleteC(user);
    }

    @Transactional
    public void createNewRole(Role role){
        roleRepository.save(role);
    }


}
