package com.co3202.taskactivityplanner.service;

import com.co3202.taskactivityplanner.model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    List<Role> findAll();
}
