package com.co3202.taskactivityplanner.service;

import com.co3202.taskactivityplanner.model.Task;
import com.co3202.taskactivityplanner.model.User;
import com.co3202.taskactivityplanner.dao.TaskDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceI implements TaskService {
    private TaskDao taskDao;

    @Override
    public void createTask(Task task) {
        taskDao.save(task);
    }

    @Override
    public void updateTask(UUID id, Task newTask) {
        Task task = taskDao.getOne(id);
        task.setName(newTask.getName());
        task.setDescription(newTask.getDescription());
        task.setDate(newTask.getDate());
        task.setTag(newTask.getTag());
        task.setPriority(newTask.getPriority());
        taskDao.save(task);
    }

    @Override
    public void deleteTask(UUID id) {
        taskDao.deleteById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public List<Task> findByOwnerOrderByDate(User user) {
        return taskDao.findByOwnerOrderByDate(user);
    }

    @Override
    public void setTaskDone(UUID id) {
        Task task = taskDao.getOne(id);
        task.setDone(true);
        taskDao.save(task);
    }

    @Override
    public void setTaskNotDone(UUID id) {
        Task task = taskDao.getOne(id);
        task.setDone(false);
        taskDao.save(task);
    }

    @Override
    public Task getTaskById(UUID id) {
        return taskDao.findById(id).orElse(null);
    }

}
