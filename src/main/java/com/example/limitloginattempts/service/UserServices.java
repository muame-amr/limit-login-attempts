package com.example.limitloginattempts.service;

import com.example.limitloginattempts.model.User;
import com.example.limitloginattempts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Service
public class UserServices {

    public static final int MAX_ATTEMPTS = 3;
    private static final long LOCK_TIME_DURATION = 1000 * 60 * 5; // 1000 milisenconds * 60 -> 1 minutes * 5 -> 5 minutes

    @Autowired
    private UserRepository userRepository;

    public void increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        userRepository.updateFailedAttempts(newFailAttempts, user.getUsername());
    }

    public void resetFailedAttempts(String username) {
        userRepository.updateFailedAttempts(0, username);
    }

    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());

        userRepository.save(user);
    }

    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();
        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis){
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
