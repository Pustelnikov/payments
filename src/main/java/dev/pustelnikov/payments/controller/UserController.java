package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
}