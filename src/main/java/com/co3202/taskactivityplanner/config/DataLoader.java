package com.co3202.taskactivityplanner.config;

import com.co3202.taskactivityplanner.model.Role;
import com.co3202.taskactivityplanner.model.Task;
import com.co3202.taskactivityplanner.model.User;
import com.co3202.taskactivityplanner.service.RoleService;
import com.co3202.taskactivityplanner.service.TaskService;
import com.co3202.taskactivityplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private UserService userService;
    private TaskService taskService;
    private RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @Autowired
    public DataLoader(UserService userService, TaskService taskService, RoleService roleService) {
        this.userService = userService;
        this.taskService = taskService;
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Creating Spring Security Roles
        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.findAll().stream().map(role -> "saved role: " + role.getRole()).forEach(logger::info);

        LocalDate today = LocalDate.now();

        // Creating default user
        User myUser1 = new User(
                "manager@mail.com",
                "pass",
                "Zain",
                "Amin");
        userService.createUser(myUser1);

        // Creating random task for myUser1
        Task myTask1 = new Task(
                "Task 1", "Urgent Task due today, and is done", today, true, "Urgent", "Tag", myUser1);
        taskService.createTask(myTask1);

        // Creating second user
        User myUser2 = new User(
                "admin@mail.com",
                "pass",
                "First",
                "Last");
        userService.createUser(myUser2);

        // Creating random task for myUser1
        Task myTask2 = new Task(
                "Task 2", "Normal Task due yesterday", today.minusDays(1), false, "Normal", "Tag", myUser1);
        taskService.createTask(myTask2);

        taskService.createTask(new Task(
                "Task 3", "Normal Task due tomorrow", today.plusDays(1), false, "Normal", "Tag", myUser1));

    }

}
