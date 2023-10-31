package com.sq018.projectmanagementapp.controller;

import com.sq018.projectmanagementapp.model.User;
import com.sq018.projectmanagementapp.pojo.UserResponse;
import com.sq018.projectmanagementapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String authPage(Model model) {
        User user  = new User();
        model.addAttribute("new_user", user);
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("new_user") User user, RedirectAttributes redirectAttributes) {
        UserResponse response = userService.createNewUser(user);
        redirectAttributes.addFlashAttribute("response message", response.getMessage());
        return  "redirect:/";
    }
}
