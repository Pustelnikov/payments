package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("register")
    public String getUserRegistrationForm() {
        return "template/registration";
    }

    @PostMapping("register")
    public String registerUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        userService.registerUser(userRegistrationRequestDto);
        return "redirect:/";
    }

    @GetMapping("login")
    public String getUserLoginForm() {
        return "template/login";
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "template/admin/users";
    }
}