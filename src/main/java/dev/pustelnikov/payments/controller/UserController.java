package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("register")
    public String getUserRegistrationPage(Model model) {
        model.addAttribute("userRegistrationRequestDto",
                new UserRegistrationRequestDto(null, null, null));
        return "template/registration";
    }

    @PostMapping("register")
    public String registerUser(@Valid UserRegistrationRequestDto userRegistrationRequestDto,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "template/registration";
        }
        userService.registerUser(userRegistrationRequestDto);
        return "redirect:/users/login";
    }

    @GetMapping("login")
    public String getUserLoginPage() {
        return "template/login";
    }

    @GetMapping("main")
    public String getUserMainPage(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("user", userService.getUserInfo(httpServletRequest.getUserPrincipal().getName()));
        return "template/main";
    }

    @GetMapping("list")
    @PreAuthorize("hasRole('ADMIN')")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "template/admin/users";
    }
}