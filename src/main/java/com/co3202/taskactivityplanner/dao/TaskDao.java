package com.co3202.taskactivityplanner.dao;

import com.co3202.taskactivityplanner.model.Task;
import com.co3202.taskactivityplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskDao extends JpaRepository<Task, UUID> {
    List<Task> findByOwnerOrderByDate(User user);
    // select t from task where t.owner = ?1

    List<Task> findByPriorityOrderByDate(String priority);

    List<Task> findByIsDone(Boolean isDone);

}
