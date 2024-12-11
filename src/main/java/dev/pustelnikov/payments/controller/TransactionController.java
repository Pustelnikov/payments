package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.transaction.DepositTransactionRequestDto;
import dev.pustelnikov.payments.dto.transaction.WithdrawTransactionRequestDto;
import dev.pustelnikov.payments.service.AccountService;
import dev.pustelnikov.payments.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    @GetMapping("deposit")
    public String getDepositForm(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("userAccounts", accountService.getUserAccounts(httpServletRequest.getUserPrincipal().getName()));
        return "template/deposit";
    }

    @PostMapping("deposit")
    public String doDeposit(DepositTransactionRequestDto depositTransactionRequestDto) {
        transactionService.doDepositTransaction(depositTransactionRequestDto);
        return "redirect:/accounts/main";
    }

    @GetMapping("withdraw")
    public String getWithdrawForm(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("userAccounts", accountService.getUserAccounts(httpServletRequest.getUserPrincipal().getName()));
        return "template/withdraw";
    }

    @PostMapping("withdraw")
    public String doWithdraw(WithdrawTransactionRequestDto withdrawTransactionRequestDto) {
        transactionService.doWithdrawTransaction(withdrawTransactionRequestDto);
        return "redirect:/accounts/main";
    }
}
