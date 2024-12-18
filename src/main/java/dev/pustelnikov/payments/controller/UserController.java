package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.user.UserDto;
import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public String registerUser(@Valid UserRegistrationRequestDto userRegistrationRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "template/registration";
        }
        userService.registerUser(userRegistrationRequestDto);
        return "redirect:/";
    }

    @GetMapping("login")
    public String getUserLoginForm() {
        return "template/login";
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers(
            @RequestParam(required = false) String searchKeyword,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "userId") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,
            Model model) {
        Page<UserDto> pageOfUsers = userService.getAllUsers(searchKeyword, pageNumber - 1, pageSize, sortField, sortDirection);
        List<UserDto> users = pageOfUsers.getContent();
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("totalItems", pageOfUsers.getTotalElements());
        model.addAttribute("totalPages", pageOfUsers.getTotalPages());
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("path", "users/all");
        model.addAttribute("users", users);
        return "template/admin/users";
    }
}