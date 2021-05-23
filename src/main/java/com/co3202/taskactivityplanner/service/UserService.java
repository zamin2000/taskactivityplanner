package com.co3202.taskactivityplanner.service;

import com.co3202.taskactivityplanner.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    User createUser(User user);

    List<User> findAll();

    User getUserByEmail(String email);

    boolean existsByEmail(String email);

    User getUserById(UUID userId); // TODO Delete?

    void deleteUser(UUID id); // TODO Delete?
}
