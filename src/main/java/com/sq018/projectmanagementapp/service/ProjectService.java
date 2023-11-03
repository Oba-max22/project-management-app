package com.sq018.projectmanagementapp.service;


import com.sq018.projectmanagementapp.model.Project;
import com.sq018.projectmanagementapp.model.User;
import com.sq018.projectmanagementapp.pojo.GenericResponse;
import com.sq018.projectmanagementapp.repository.ProjectRepository;
import com.sq018.projectmanagementapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public GenericResponse<Project> createProject(Project project, User user, HttpSession httpSession) {
        GenericResponse<Project> response = new GenericResponse<>();
        User foundUser = userRepository.findById(user.getId()).get();

        try {
            Project savedProject = projectRepository.save(project);

            Set<Project> projectSet = foundUser.getProjects();
            projectSet.add(savedProject);
            foundUser.setProjects(projectSet);

            User savedUser = userRepository.save(foundUser);

            // update user session data
            httpSession.setAttribute("currentUser", savedUser);

            response.setData(savedProject);
            response.setIsSuccessful(true);
            response.setMessage("Project created successfully!");
        } catch (Exception ex) {
            response.setIsSuccessful(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public List<Project> getAllProjectsByUser(User loggedInUser) {

        List<Project> result = projectRepository.findAllByUsers(loggedInUser);

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
