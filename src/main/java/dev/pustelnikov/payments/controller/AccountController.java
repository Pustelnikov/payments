package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("main")
    public String getMainPage(HttpServletRequest httpServletRequest, Model model) {
        String userName = httpServletRequest.getUserPrincipal().getName();
        model.addAttribute("userName", userName);
        model.addAttribute("userAccounts", accountService.getUserAccounts(userName));
        return "template/main";
    }
}