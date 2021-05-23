package com.co3202.taskactivityplanner.dao;

import com.co3202.taskactivityplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
