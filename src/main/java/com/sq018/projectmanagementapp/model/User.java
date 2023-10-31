package com.sq018.projectmanagementapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_projects",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    Set<Project> userProjects;
}

/*
*
* 1 - User
* 2 - Project
* 3 - Tasks [ 'Todo', 'In Progress', 'In Review' 'Done' ]
* User > Projects > Tasks
*
* User oneToMany / ManyToMany Projects
* Projects oneToMany Tasks
*
*
* */