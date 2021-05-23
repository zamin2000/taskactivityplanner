package com.co3202.taskactivityplanner.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity(name = "User")
@Table(name = "User") // Naming the database table as User for readability
@Getter // Lombok auto-generated getters
@Setter // Lombok auto-generated setters
@NoArgsConstructor // Lombok auto-generated constructor
@AllArgsConstructor // Lombok auto-generated constructor
@EqualsAndHashCode // Lombok auto-generated equal-to and hash code classes
public class User {
    @Id // Designates 'user_id' column as the primary key
    @GeneratedValue
    @Type(type = "uuid-char") // Avoids creating column with Binary type, forces VARCHAR
    @Column(name = "user_id") // Defining column name in the database as user_id to avoid clash
    private UUID id; // Defining the actual id attribute in Spring
    @Email() // Flags the email attribute for Spring Validation
    @NotEmpty(message = "Enter your email address") // Adds a Not Null rule in database for this column
    @Column(unique = true) // Insists that no duplicate emails be used for multiple users.
    private String email; // Email attribute
    @NotEmpty(message = "Choose a password")
    @Size(min = 6, message = "At least 6 characters")
    private String password; // Password attribute
    @NotEmpty(message = "Enter your first name")
    private String firstname; // First name attribute
    @NotEmpty(message = "Enter your last name")
    private String lastname; // Last name attribute
    private String colorCode;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST) // One to Many relationship declared
    private List<Task> tasksOwned; // List to declare link from User to Task class.

    @ManyToMany(cascade = CascadeType.MERGE) // Define many-to-many relationship between User and Role.
    // Creates new join table 'user_role' for storing the privilege roles of users.
    // Joined together by primary key of each table.
    // Stored in list of type Role as 'roles'.
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    // Constructor needed for inserting sample data.
    public User(@Email @NotEmpty String email,
                @NotEmpty String password,
                @NotEmpty String firstname,
                @NotEmpty String lastname)
    {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Returns a stream of those tasks which are not finished/still in progress.
    public List<Task> getTasksIsNotDone() {
        return tasksOwned.stream()
                .filter(task -> !task.isDone())
                .collect(Collectors.toList());
    }

    // Returns a stream of those tasks which are finished and complete.
    public List<Task> getTasksIsDone() {
        return tasksOwned.stream()
                .filter(task -> task.isDone())
                .collect(Collectors.toList());
    }


    // Returns a stream of those tasks which are Trivial priority.
    public List<Task> getTasksPriorityTrivial() {
        return tasksOwned.stream()
                .filter(task -> task.getPriority().equals("Trivial"))
                .collect(Collectors.toList());
    }

    // Returns a stream of those tasks which are Normal priority.
    public List<Task> getTasksPriorityNormal() {
        return tasksOwned.stream()
                .filter(task -> task.getPriority().equals("Normal"))
                .collect(Collectors.toList());
    }

    // Returns a stream of those tasks which are Urgent priority.
    public List<Task> getTasksPriorityUrgent() {
        return tasksOwned.stream()
                .filter(task -> task.getPriority().equals("Urgent"))
                .collect(Collectors.toList());
    }

}
