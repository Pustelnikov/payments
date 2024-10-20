package dev.pustelnikov.payments.exception.account;

public class AccountInsufficientFundsException extends RuntimeException {
    public AccountInsufficientFundsException(String message) {
        super(message);
    }
}
