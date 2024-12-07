package dev.pustelnikov.payments.advice;

import dev.pustelnikov.payments.exception.UserAlreadyExistsException;
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
}
