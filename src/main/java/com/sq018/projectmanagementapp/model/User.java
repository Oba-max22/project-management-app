package com.sq018.projectmanagementapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
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

//    @ManyToMany
//    @JoinTable(
//            name = "user_project",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id"))
//    Set<Project> projects = new HashSet<>();

    @ManyToMany(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "project_user", joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "project_id", nullable = false)})
    Set<Project> projects = new HashSet<>();

}