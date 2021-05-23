package com.co3202.taskactivityplanner.dao;

import com.co3202.taskactivityplanner.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByRole(String user);
}
