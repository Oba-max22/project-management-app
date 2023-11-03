package com.sq018.projectmanagementapp.controller;

import com.sq018.projectmanagementapp.model.Project;
import com.sq018.projectmanagementapp.model.User;
import com.sq018.projectmanagementapp.pojo.GenericResponse;
import com.sq018.projectmanagementapp.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class ProjectsController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public String getAllProjects(Model model, HttpSession httpSession) {
        User loggedInUser = (User) httpSession.getAttribute("currentUser");
        if (loggedInUser  == null) {
            return "redirect:/";
        }

        List<Project> projects = projectService.getAllProjectsByUser(loggedInUser);

        model.addAttribute("projects", projects);
        model.addAttribute("project", new Project());
        return "projects";
    }


    @PostMapping("/create-project")
    public String createProject(@ModelAttribute("project") Project project, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        User loggedInUser = (User) httpSession.getAttribute("currentUser");
        if (loggedInUser == null) {
            return "redirect:/";
        }
        GenericResponse<Project> response = projectService.createProject(project, loggedInUser, httpSession);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return  "redirect:/projects";
    }
}
