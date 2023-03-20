package com.example.limitloginattempts.util;

import com.example.limitloginattempts.model.Role;
import com.example.limitloginattempts.model.User;
import com.example.limitloginattempts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Role role = new Role(1, "superadmin");
        userRepository.save(new User(1, "admin", encoder.encode("password"), true, true, 0, null, new HashSet<>(Arrays.asList(role))));
    }
}
