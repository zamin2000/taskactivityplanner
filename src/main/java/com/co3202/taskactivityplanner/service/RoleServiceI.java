package com.co3202.taskactivityplanner.service;

import com.co3202.taskactivityplanner.model.Role;
import com.co3202.taskactivityplanner.dao.RoleDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceI implements RoleService {
    private RoleDao roleDao;

    @Override
    public Role createRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
