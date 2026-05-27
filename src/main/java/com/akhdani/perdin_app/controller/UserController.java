package com.akhdani.perdin_app.controller;

import com.akhdani.perdin_app.dto.UserRequest;
import com.akhdani.perdin_app.enums.Role;
import com.akhdani.perdin_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String userPage(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "user-list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("user", new UserRequest());
        model.addAttribute("roles", Role.values());
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute UserRequest request) {
        userService.saveUser(request);
        return "redirect:/admin/user";
    }
}
