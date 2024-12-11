package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.dto.transaction.*;
import dev.pustelnikov.payments.exception.account.AccountCurrencyMismatchException;
import dev.pustelnikov.payments.exception.account.AccountInsufficientFundsException;
import dev.pustelnikov.payments.exception.account.AccountLockedException;
import dev.pustelnikov.payments.model.TransactionStatus;
import dev.pustelnikov.payments.model.TransactionType;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import dev.pustelnikov.payments.model.entity.TransactionEntity;
import dev.pustelnikov.payments.repository.TransactionRepo;
import dev.pustelnikov.payments.service.AccountService;
import dev.pustelnikov.payments.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final AccountService accountService;

    private String generateTransactionUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    @Transactional
    public void doDepositTransaction(DepositTransactionRequestDto depositTransactionRequestDto) throws AccountLockedException {
        Long accountEntityId = depositTransactionRequestDto.getAccountId();
        BigDecimal transactionAmount = depositTransactionRequestDto.getTransactionAmount();
        AccountEntity accountEntity = accountService.findAccountById(accountEntityId);

        if (!accountService.isAccountActive(accountEntity)) {
            throw new AccountLockedException("Account %s is not active".formatted(accountEntity.getAccountNumber()));
        }

        accountEntity.setAccountBalance(accountEntity.getAccountBalance().add(transactionAmount));
        accountService.saveAccountEntity(accountEntity);
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .transactionUuid(this.generateTransactionUuid())
                .transactionType(TransactionType.DEPOSIT)
                .oppositeAccountNumber("External")
                .transactionAmount(transactionAmount)
                .transactionTimestamp(LocalDateTime.now())
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .account(accountEntity)
                .build();
        transactionRepo.save(transactionEntity);
    }

    @Override
    @Transactional
    public void doWithdrawTransaction(WithdrawTransactionRequestDto withdrawTransactionRequestDto)
            throws AccountLockedException, AccountInsufficientFundsException {
        Long accountEntityId = withdrawTransactionRequestDto.getAccountId();
        BigDecimal transactionAmount = withdrawTransactionRequestDto.getTransactionAmount();
        AccountEntity accountEntity = accountService.findAccountById(accountEntityId);

        if (!accountService.isAccountActive(accountEntity)) {
            throw new AccountLockedException("Account %s is not active".formatted(accountEntity.getAccountNumber()));
        }
        if (!accountService.isAccountBalanceValid(accountEntity, transactionAmount)) {
            throw new AccountInsufficientFundsException("Account number %s has insufficient funds".formatted(accountEntity.getAccountNumber()));
        }

        accountEntity.setAccountBalance(accountEntity.getAccountBalance().subtract(transactionAmount));
        accountService.saveAccountEntity(accountEntity);
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .transactionUuid(this.generateTransactionUuid())
                .transactionType(TransactionType.WITHDRAW)
                .oppositeAccountNumber("External")
                .transactionAmount(transactionAmount)
                .transactionTimestamp(LocalDateTime.now())
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .account(accountEntity)
                .build();
        transactionRepo.save(transactionEntity);
    }

    @Override
    @Transactional
    public void doTransferTransaction(TransferTransactionRequestDto transferTransactionRequestDto)
            throws AccountLockedException, AccountInsufficientFundsException, AccountCurrencyMismatchException {
        Long accountEntityId = transferTransactionRequestDto.getAccountId();
        String oppositeAccountNumber = transferTransactionRequestDto.getOppositeAccountNumber();
        BigDecimal transactionAmount = transferTransactionRequestDto.getTransactionAmount();
        AccountEntity accountEntity = accountService.findAccountById(accountEntityId);
        AccountEntity oppositeAccountEntity = accountService.findAccountByNumber(oppositeAccountNumber);

        if (!accountService.isAccountActive(accountEntity)) {
            throw new AccountLockedException("Account number %s is not active".formatted(accountEntity.getAccountNumber()));
        }
        if (!accountService.isAccountActive(oppositeAccountEntity)) {
            throw new AccountLockedException("Account number %s is not active".formatted(oppositeAccountEntity.getAccountNumber()));
        }
        if (!accountService.isAccountBalanceValid(accountEntity, transactionAmount)) {
            throw new AccountInsufficientFundsException("Account number %s has insufficient funds".formatted(accountEntity.getAccountNumber()));
        }
        if (!accountService.isAccountCurrencyValid(accountEntity, oppositeAccountEntity.getAccountCurrency())) {
            throw new AccountCurrencyMismatchException("Accounts %s and %s has different currency type"
                    .formatted(accountEntity.getAccountNumber(), oppositeAccountEntity.getAccountNumber()));
        }

        accountEntity.setAccountBalance(accountEntity.getAccountBalance().subtract(transactionAmount));
        accountService.saveAccountEntity(accountEntity);
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .transactionUuid(this.generateTransactionUuid())
                .transactionType(TransactionType.TRANSFER)
                .oppositeAccountNumber(oppositeAccountNumber)
                .transactionAmount(transactionAmount)
                .transactionTimestamp(LocalDateTime.now())
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .account(accountEntity)
                .build();
        transactionRepo.save(transactionEntity);

        oppositeAccountEntity.setAccountBalance(oppositeAccountEntity.getAccountBalance().add(transactionAmount));
        accountService.saveAccountEntity(oppositeAccountEntity);
        TransactionEntity oppositeTransactionEntity = TransactionEntity.builder()
                .transactionUuid(this.generateTransactionUuid())
                .transactionType(TransactionType.TRANSFER)
                .oppositeAccountNumber(accountEntity.getAccountNumber())
                .transactionAmount(transactionAmount)
                .transactionTimestamp(LocalDateTime.now())
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .account(oppositeAccountEntity)
                .build();
        transactionRepo.save(oppositeTransactionEntity);
    }

    @Override
    @Transactional
    public void doPaymentTransaction(PaymentTransactionRequestDto paymentTransactionRequestDto)
            throws AccountLockedException, AccountInsufficientFundsException, AccountCurrencyMismatchException {
        Long accountEntityId = paymentTransactionRequestDto.getAccountId();
        String oppositeAccountNumber = paymentTransactionRequestDto.getOppositeAccountNumber();
        BigDecimal transactionAmount = paymentTransactionRequestDto.getTransactionAmount();
        AccountEntity accountEntity = accountService.findAccountById(accountEntityId);
        AccountEntity oppositeAccountEntity = accountService.findAccountByNumber(oppositeAccountNumber);

        if (!accountService.isAccountActive(accountEntity)) {
            throw new AccountLockedException("Account number %s is not active".formatted(accountEntity.getAccountNumber()));
        }
        if (!accountService.isAccountActive(oppositeAccountEntity)) {
            throw new AccountLockedException("Account number %s is not active".formatted(oppositeAccountEntity.getAccountNumber()));
        }
        if (!accountService.isAccountBalanceValid(accountEntity, transactionAmount)) {
            throw new AccountInsufficientFundsException("Account number %s has insufficient funds".formatted(accountEntity.getAccountNumber()));
        }
        if (!accountService.isAccountCurrencyValid(accountEntity, oppositeAccountEntity.getAccountCurrency())) {
            throw new AccountCurrencyMismatchException("Accounts %s and %s has different currency type"
                    .formatted(accountEntity.getAccountNumber(), oppositeAccountEntity.getAccountNumber()));
        }

        accountEntity.setAccountBalance(accountEntity.getAccountBalance().subtract(transactionAmount));
        accountService.saveAccountEntity(accountEntity);
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .transactionUuid(this.generateTransactionUuid())
                .transactionType(TransactionType.PAYMENT)
                .oppositeAccountNumber(oppositeAccountNumber)
                .transactionAmount(transactionAmount)
                .transactionTimestamp(LocalDateTime.now())
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .account(accountEntity)
                .build();
        transactionRepo.save(transactionEntity);

        oppositeAccountEntity.setAccountBalance(oppositeAccountEntity.getAccountBalance().add(transactionAmount));
        accountService.saveAccountEntity(oppositeAccountEntity);
        TransactionEntity oppositeTransactionEntity = TransactionEntity.builder()
                .transactionUuid(this.generateTransactionUuid())
                .transactionType(TransactionType.PAYMENT)
                .oppositeAccountNumber(accountEntity.getAccountNumber())
                .transactionAmount(transactionAmount)
                .transactionTimestamp(LocalDateTime.now())
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .account(oppositeAccountEntity)
                .build();
        transactionRepo.save(oppositeTransactionEntity);
    }
}
