package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.dto.transaction.*;
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
}
