package dev.pustelnikov.payments.controller;

import dev.pustelnikov.payments.dto.transaction.DepositTransactionRequestDto;
import dev.pustelnikov.payments.dto.transaction.PaymentTransactionRequestDto;
import dev.pustelnikov.payments.dto.transaction.TransferTransactionRequestDto;
import dev.pustelnikov.payments.dto.transaction.WithdrawTransactionRequestDto;
import dev.pustelnikov.payments.service.AccountService;
import dev.pustelnikov.payments.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("transfer")
    public String getTransferForm(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("userAccounts", accountService.getUserAccounts(httpServletRequest.getUserPrincipal().getName()));
        return "template/transfer";
    }

    @PostMapping("transfer")
    public String doTransfer(TransferTransactionRequestDto transferTransactionRequestDto) {
        transactionService.doTransferTransaction(transferTransactionRequestDto);
        return "redirect:/accounts/main";
    }

    @GetMapping("payment")
    public String getPaymentForm(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("userAccounts", accountService.getUserAccounts(httpServletRequest.getUserPrincipal().getName()));
        return "template/payment";
    }

    @PostMapping("payment")
    public String doPayment(PaymentTransactionRequestDto paymentTransactionRequestDto) {
        transactionService.doPaymentTransaction(paymentTransactionRequestDto);
        return "redirect:/accounts/main";
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "template/admin/transactions";
    }
}
