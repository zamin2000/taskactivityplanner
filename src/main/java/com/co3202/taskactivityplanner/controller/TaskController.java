package com.co3202.taskactivityplanner.controller;

import com.co3202.taskactivityplanner.model.Task;
import com.co3202.taskactivityplanner.model.User;
import com.co3202.taskactivityplanner.service.TaskService;
import com.co3202.taskactivityplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class TaskController {

    private UserService userService;
    private TaskService taskService;

    @GetMapping("/tasks")
    public String showTasks(Model model, Principal principal) {
        // Authentication
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        // Sending task object to thymeleaf, to access user tasks
        model.addAttribute("user", user);
        model.addAttribute("tasksOwned", taskService.findByOwnerOrderByDate(user));
        return "tasks";
    }

    // Sends add task form with task owner pre-set
    @GetMapping("/add-task")
    public String newTask(Model model, Principal principal, SecurityContextHolderAwareRequestWrapper request) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        // When sending new task form, also sending a pre-filled Task with the user info details pre-entered.
        // So that we can assign this Task to the User
        Task task = new Task();
        task.setOwner(user);
        model.addAttribute("task", task);

        return "add-task";
    }

    // Submitting a new task
    @PostMapping("/add-task")
    public String saveTask(@Valid Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage","One or more of your inputs aren't valid.");
            return "error";
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    // Sending the Task to populate the form
    @GetMapping("/edit-task/{id}")
    public String populateTask(@PathVariable UUID id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "edit-task";
    }

    // Saving the changes of newly edited task
    @PostMapping("/edit-task/{id}")
    public String updateTask(@Valid Task task, BindingResult result, @PathVariable UUID id, Model model) {
        if (result.hasErrors()) {
            return "task";
        }
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    @PostMapping("/set-done/{id}")
    public String setTaskDone(@PathVariable UUID id) {
        taskService.setTaskDone(id);
        return "redirect:/tasks";
    }

    @PostMapping("/set-undone/{id}")
    public String setTaskNotDone(@PathVariable UUID id) {
        taskService.setTaskNotDone(id);
        return "redirect:/tasks";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }


    @GetMapping("/urgent-tasks")
    public String showUrgentTasks(Model model, Principal principal) {
        // Authentication
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        // Sending List of Tasks from method getTasksPriorityXXX to thymeleaf
        // To display user's specific tasks of said priority
        model.addAttribute("user", user);
        model.addAttribute("tasksOwned", user.getTasksPriorityUrgent());
        return "urgent-tasks";
    }

    @GetMapping("/normal-tasks")
    public String showNormalTasks(Model model, Principal principal) {
        // Authentication
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        // Sending List of Tasks from method getTasksPriorityXXX to thymeleaf
        // To display user's specific tasks of said priority
        model.addAttribute("user", user);
        model.addAttribute("tasksOwned", user.getTasksPriorityNormal());
        return "normal-tasks";
    }

    @GetMapping("/trivial-tasks")
    public String showTrivialTasks(Model model, Principal principal) {
        // Authentication
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        // Sending List of Tasks from method getTasksPriorityXXX to thymeleaf
        // To display user's specific tasks of said priority
        model.addAttribute("user", user);
        model.addAttribute("tasksOwned", user.getTasksPriorityTrivial());
        return "trivial-tasks";
    }

    @GetMapping("/finished-tasks")
    public String showFinishedTasks(Model model, Principal principal) {
        // Authentication
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        // Sending List of Tasks from method getTasksPriorityXXX to thymeleaf
        // To display user's specific tasks of said priority
        model.addAttribute("user", user);
        model.addAttribute("tasksOwned", user.getTasksIsDone());
        return "finished-tasks";
    }

}
