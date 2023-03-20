package com.example.limitloginattempts.service;

import com.example.limitloginattempts.model.CustomUserDetails;
import com.example.limitloginattempts.model.User;
import com.example.limitloginattempts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServices userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not exists");
        }
        return new CustomUserDetails(user);
    }
}
