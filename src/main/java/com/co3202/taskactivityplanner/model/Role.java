package com.co3202.taskactivityplanner.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Role")
@Table(name = "Role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "role")
    private String role;
    // Joins on to previously defined 'account_role' table
    // Stored in list of type User as 'accounts'.
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, List<User> users) {
        this.role = role;
        this.users = users;
    }
}
