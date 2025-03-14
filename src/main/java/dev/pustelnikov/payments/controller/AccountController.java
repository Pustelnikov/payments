package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.account.AccountRegistrationRequestDto;
import dev.pustelnikov.payments.model.UserRole;
import dev.pustelnikov.payments.service.AccountCheckService;
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
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountCheckService accountCheckService;

    @GetMapping("main")
    public String getMainPage(HttpServletRequest httpServletRequest, Model model) {
        String userName = httpServletRequest.getUserPrincipal().getName();
        model.addAttribute("userName", userName);
        model.addAttribute("userAccounts", accountService.getUserAccounts(userName));
        return "template/main";
    }

    @PostMapping("register")
    public String registerAccount(AccountRegistrationRequestDto accountRegistrationRequestDto, HttpServletRequest httpServletRequest) {
        if (!(Objects.equals(httpServletRequest.getUserPrincipal().getName(), accountRegistrationRequestDto.getUserName())
                || httpServletRequest.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/accounts/main";
        }
        accountService.registerAccount(accountRegistrationRequestDto);
        return "redirect:/accounts/main";
    }

    @GetMapping("{accountId:\\d+}")
    public String getAccount(@PathVariable Long accountId, HttpServletRequest httpServletRequest, Model model) {
        if (!(accountCheckService.isAccountBelongsToUser(accountId, httpServletRequest.getUserPrincipal().getName())
                || httpServletRequest.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/accounts/main";
        }
        model.addAttribute("account", accountService.getAccountInfo(accountId));
        return "template/account";
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "template/admin/accounts";
    }

    @PostMapping("lock")
    public String lockAccount(Long accountId, HttpServletRequest httpServletRequest) {
        if (!(accountCheckService.isAccountBelongsToUser(accountId, httpServletRequest.getUserPrincipal().getName())
                || httpServletRequest.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/accounts/main";
        }
        accountService.lockAccount(accountId);
        return "redirect:/accounts/%d".formatted(accountId);
    }

    @PostMapping("unlock")
    @PreAuthorize("hasRole('ADMIN')")
    public String unlockAccount(Long accountId) {
        accountService.unlockAccount(accountId);
        return "redirect:/accounts/%d".formatted(accountId);
    }
}
