package com.example.limitloginattempts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String HomePage() {
        return "Homepage";
    }

    @GetMapping("/error")
    public String ErrorPage() {
        return "Error! Something is wrong!";
    }
}
