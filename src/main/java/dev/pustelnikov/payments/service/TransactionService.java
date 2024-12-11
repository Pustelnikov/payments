package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.transaction.*;

public interface TransactionService {
    void doDepositTransaction(DepositTransactionRequestDto depositTransactionRequestDto);
    void doWithdrawTransaction(WithdrawTransactionRequestDto withdrawTransactionRequestDto);
    void doTransferTransaction(TransferTransactionRequestDto transferTransactionRequestDto);
    void doPaymentTransaction(PaymentTransactionRequestDto paymentTransactionRequestDto);
}
