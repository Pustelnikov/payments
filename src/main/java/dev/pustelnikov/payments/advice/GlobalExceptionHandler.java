package dev.pustelnikov.payments.advice;

import dev.pustelnikov.payments.exception.account.*;
import dev.pustelnikov.payments.exception.card.*;
import dev.pustelnikov.payments.exception.user.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.CONFLICT.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(CardNotFoundException.class)
    public String handleCardNotFoundException(CardNotFoundException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(CardAlreadyExistException.class)
    public String handleCardAlreadyExistException(CardAlreadyExistException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.CONFLICT.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public String handleAccountNotFoundException(AccountNotFoundException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "template/error";
    }

    @ExceptionHandler(AccountAlreadyExistException.class)
    public String handleAccountAlreadyExistException(AccountAlreadyExistException exception, HttpServletResponse response, Model model) {
        response.setStatus(HttpStatus.CONFLICT.value());
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
