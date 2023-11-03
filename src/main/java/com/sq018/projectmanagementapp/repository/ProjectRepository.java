package com.sq018.projectmanagementapp.repository;

import com.sq018.projectmanagementapp.model.Project;
import com.sq018.projectmanagementapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

    List<Project> findAllByUsers(User user);
}
