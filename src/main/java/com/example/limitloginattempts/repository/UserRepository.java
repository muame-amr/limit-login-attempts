package com.example.limitloginattempts.repository;

import com.example.limitloginattempts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    @Modifying
    public void updateFailedAttempts(int attempts, String username);
}
