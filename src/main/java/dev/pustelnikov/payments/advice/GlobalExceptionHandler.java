package dev.pustelnikov.payments.advice;

import dev.pustelnikov.payments.exception.account.AccountCurrencyMismatchException;
import dev.pustelnikov.payments.exception.account.AccountInsufficientFundsException;
import dev.pustelnikov.payments.exception.account.AccountLockedException;
import dev.pustelnikov.payments.exception.account.AccountNotFoundException;
import dev.pustelnikov.payments.exception.user.UserAlreadyExistsException;
import dev.pustelnikov.payments.exception.user.UserNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.CONFLICT.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public String handleAccountNotFoundException(AccountNotFoundException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(AccountLockedException.class)
    public String handleAccountLockedException(AccountLockedException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.LOCKED.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(AccountInsufficientFundsException.class)
    public String handleAccountInsufficientFundsException(AccountInsufficientFundsException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(AccountCurrencyMismatchException.class)
    public String handleAccountCurrencyMismatchException(AccountCurrencyMismatchException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }
}
