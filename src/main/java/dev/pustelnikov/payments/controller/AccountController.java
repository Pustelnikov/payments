package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.account.AccountLockRequestDto;
import dev.pustelnikov.payments.dto.account.AccountRegistrationRequestDto;
import dev.pustelnikov.payments.dto.account.AccountUnlockRequestDto;
import dev.pustelnikov.payments.model.UserRole;
import dev.pustelnikov.payments.service.AccountService;
import dev.pustelnikov.payments.service.UserService;
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
    private final UserService userService;

    @PostMapping("register")
    public String registerAccount(AccountRegistrationRequestDto accountRegistrationRequestDto, HttpServletRequest request) {
        if (!(Objects.equals(accountRegistrationRequestDto.getUserId(),
                userService.findUserByUserName(request.getUserPrincipal().getName()).getUserId())
                || request.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/users/main";
        }
        accountService.registerAccount(accountRegistrationRequestDto);
        return "redirect:/users/main";
    }

    @GetMapping("{accountId:\\d+}")
    public String getAccountMainPage(@PathVariable Long accountId, HttpServletRequest request, Model model) {
        if (!(accountService.isAccountBelongsToUser(accountId, request.getUserPrincipal().getName())
                || request.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/users/main";
        }
        model.addAttribute("account", accountService.getAccountInfo(accountId));
        return "template/account";
    }

    @PostMapping("lock")
    public String lockAccount(AccountLockRequestDto accountLockRequestDto, HttpServletRequest request) {
        if (!(accountService.isAccountBelongsToUser(accountLockRequestDto.getAccountId(), request.getUserPrincipal().getName())
                || request.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/users/main";
        }
        accountService.lockAccount(accountLockRequestDto);
        return "redirect:/accounts/%d".formatted(accountLockRequestDto.getAccountId());
    }

    @PostMapping("unlock")
    @PreAuthorize("hasRole('ADMIN')")
    public String unlockAccount(AccountUnlockRequestDto accountUnlockRequestDto) {
        accountService.unlockAccount(accountUnlockRequestDto);
        return "redirect:/accounts/%d".formatted(accountUnlockRequestDto.getAccountId());
    }

    @GetMapping("list")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAccountList(Model model) {
        model.addAttribute("accounts", accountService.getAccountList());
        return "template/admin/accounts";
    }
}