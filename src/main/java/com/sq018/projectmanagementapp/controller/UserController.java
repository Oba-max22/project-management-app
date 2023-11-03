package com.sq018.projectmanagementapp.controller;

import com.sq018.projectmanagementapp.model.User;
import com.sq018.projectmanagementapp.pojo.LoginRequest;
import com.sq018.projectmanagementapp.pojo.UserResponse;
import com.sq018.projectmanagementapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String register(Model model) {
        User user  = new User();
        model.addAttribute("new_user", user);
        model.addAttribute("login_request", new LoginRequest());
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("new_user") User user, RedirectAttributes redirectAttributes) {
        UserResponse response = userService.createNewUser(user);
        redirectAttributes.addFlashAttribute("response message", response.getMessage());
        return  "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login_request", new LoginRequest());
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login_request") LoginRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        UserResponse response = userService.doLogin(request);
        if(response.getIsSuccessful()) {
            session.setAttribute("currentUser", response.getUser());
            String message = "Welcome Back " + response.getUser().getFirstName() + " " + response.getUser().getFirstName();
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/projects";
        }
        redirectAttributes.addFlashAttribute("error", response.getMessage());
        return  "redirect:/";
    }
}
