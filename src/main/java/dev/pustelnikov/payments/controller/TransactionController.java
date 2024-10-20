package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.transaction.DepositTransactionRequestDto;
import dev.pustelnikov.payments.dto.transaction.PaymentTransactionRequestDto;
import dev.pustelnikov.payments.dto.transaction.TransferTransactionRequestDto;
import dev.pustelnikov.payments.dto.transaction.WithdrawTransactionRequestDto;
import dev.pustelnikov.payments.dto.user.UserDto;
import dev.pustelnikov.payments.model.UserRole;
import dev.pustelnikov.payments.service.AccountService;
import dev.pustelnikov.payments.service.TransactionService;
import dev.pustelnikov.payments.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;
    private final UserService userService;

    @ModelAttribute("user")
    public UserDto getUserInfo(HttpServletRequest httpServletRequest) {
        return userService.getUserInfo(httpServletRequest.getUserPrincipal().getName());
    }

    @GetMapping("transfer")
    public String getTransferPage(Model model) {
        model.addAttribute("transferTransactionRequestDto",
                new TransferTransactionRequestDto(null, null, null));
        return "template/transfer";
    }

    @PostMapping("transfer")
    public String doTransfer(@Valid TransferTransactionRequestDto transferTransactionRequestDto,
                             BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "template/transfer";
        }
        if (!(accountService.isAccountBelongsToUser(transferTransactionRequestDto.getAccountId(), request.getUserPrincipal().getName())
                || request.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/users/main";
        }
        transactionService.doTransferTransaction(transferTransactionRequestDto);
        return "redirect:/users/main";
    }

    @GetMapping("payment")
    public String getPaymentPage(Model model) {
        model.addAttribute("paymentTransactionRequestDto",
                new PaymentTransactionRequestDto(null, null, null));
        return "template/payment";
    }

    @PostMapping("payment")
    public String doPayment(@Valid PaymentTransactionRequestDto paymentTransactionRequestDto,
                            BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "template/payment";
        }
        if (!(accountService.isAccountBelongsToUser(paymentTransactionRequestDto.getAccountId(), request.getUserPrincipal().getName())
                || request.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/users/main";
        }
        transactionService.doPaymentTransaction(paymentTransactionRequestDto);
        return "redirect:/users/main";
    }

    @GetMapping("deposit")
    public String getDepositPage(Model model) {
        model.addAttribute("depositTransactionRequestDto",
                new DepositTransactionRequestDto(null, null));
        return "template/deposit";
    }

    @PostMapping("deposit")
    public String doDeposit(@Valid DepositTransactionRequestDto depositTransactionRequestDto,
                            BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "template/deposit";
        }
        if (!(accountService.isAccountBelongsToUser(depositTransactionRequestDto.getAccountId(), request.getUserPrincipal().getName())
                || request.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/users/main";
        }
        transactionService.doDepositTransaction(depositTransactionRequestDto);
        return "redirect:/users/main";
    }

    @GetMapping("withdraw")
    public String getWithdrawPage(Model model) {
        model.addAttribute("withdrawTransactionRequestDto",
                new WithdrawTransactionRequestDto(null, null));
        return "template/withdraw";
    }

    @PostMapping("withdraw")
    public String doWithdraw(@Valid WithdrawTransactionRequestDto withdrawTransactionRequestDto,
                             BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "template/withdraw";
        }
        if (!(accountService.isAccountBelongsToUser(withdrawTransactionRequestDto.getAccountId(), request.getUserPrincipal().getName())
                || request.isUserInRole(UserRole.ROLE_ADMIN.name()))) {
            return "redirect:/users/main";
        }
        transactionService.doWithdrawTransaction(withdrawTransactionRequestDto);
        return "redirect:/users/main";
    }

    @GetMapping("list")
    @PreAuthorize("hasRole('ADMIN')")
    public String getTransactionList(Model model) {
        model.addAttribute("transactions", transactionService.getTransactionList());
        return "template/admin/transactions";
    }
}
