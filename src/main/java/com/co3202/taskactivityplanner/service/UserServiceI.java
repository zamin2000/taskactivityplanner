package com.co3202.taskactivityplanner.service;

import com.co3202.taskactivityplanner.model.Role;
import com.co3202.taskactivityplanner.model.User;
import com.co3202.taskactivityplanner.dao.RoleDao;
import com.co3202.taskactivityplanner.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceI implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleDao.findByRole("USER");
        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        return userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Override
    public User getUserById(UUID id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(UUID id) {
        User user = userDao.getOne(id);
        user.getTasksOwned().forEach(task -> task.setOwner(null));
        userDao.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
