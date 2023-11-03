package com.sq018.projectmanagementapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "projects")
    private Set<User> users = new HashSet<>();
}
