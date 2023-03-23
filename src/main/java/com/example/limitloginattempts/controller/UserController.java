package com.example.limitloginattempts.controller;

import com.example.limitloginattempts.model.User;
import com.example.limitloginattempts.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServices userService;

    @GetMapping("/")
    public String HomePage() {
        return "Homepage";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/error")
    public String ErrorPage() {
        return "Error! Something is wrong!";
    }
}
