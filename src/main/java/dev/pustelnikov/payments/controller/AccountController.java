package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.account.AccountRegistrationRequestDto;
import dev.pustelnikov.payments.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("register")
    public String registerAccount(AccountRegistrationRequestDto accountRegistrationRequestDto) {
        accountService.registerAccount(accountRegistrationRequestDto);
        return "redirect:/accounts/main";
    }

    @GetMapping("{accountId:\\d+}")
    public String getAccount(@PathVariable Long accountId, Model model) {
        model.addAttribute("account", accountService.getAccountInfo(accountId));
        return "template/account";
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "template/admin/accounts";
    }
}