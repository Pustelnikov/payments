package dev.pustelnikov.payments.exception.account;

public class AccountCurrencyMismatchException extends RuntimeException {
    public AccountCurrencyMismatchException(String message) {
        super(message);
    }
}
