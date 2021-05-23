package com.co3202.taskactivityplanner.service;

import com.co3202.taskactivityplanner.model.Task;
import com.co3202.taskactivityplanner.model.User;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    void createTask(Task task);

    void updateTask(UUID id, Task task);

    void deleteTask(UUID id);

    List<Task> findAll();

    List<Task> findByOwnerOrderByDate(User user);

    void setTaskDone(UUID id);

    void setTaskNotDone(UUID id);

    Task getTaskById(UUID taskId);

}
