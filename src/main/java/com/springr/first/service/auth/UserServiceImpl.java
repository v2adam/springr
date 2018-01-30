package com.springr.first.service.auth;

import com.springr.first.domain.Role;
import com.springr.first.domain.User;
import com.springr.first.repo.RoleRepository;
import com.springr.first.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final String USER_ROLE = "USER";

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


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }

        return new UserDetailsImpl(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public String registerUser(User userToRegister) {
        User userCheck = userRepository.findByUserName(userToRegister.getUserName());

        if (userCheck != null) {
            return "alreadyExists";
        }

        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole != null) {
            userToRegister.getRoles().add(userRole);
        } else {
            userToRegister.addRoles(USER_ROLE);
        }

        userRepository.save(userToRegister);
        return "ok";
    }

}