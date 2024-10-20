package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.account.AccountDto;
import dev.pustelnikov.payments.dto.account.AccountLockRequestDto;
import dev.pustelnikov.payments.dto.account.AccountRegistrationRequestDto;
import dev.pustelnikov.payments.dto.account.AccountUnlockRequestDto;
import dev.pustelnikov.payments.model.AccountCurrency;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void registerAccount(AccountRegistrationRequestDto accountRegistrationRequestDto);
    AccountDto getAccountInfo(Long accountId);
    void lockAccount(AccountLockRequestDto accountLockRequestDto);
    void unlockAccount(AccountUnlockRequestDto accountUnlockRequestDto);
    List<AccountDto> getAccountList();
    boolean isAccountActive(AccountEntity accountEntity);
    boolean isAccountBalanceValid(AccountEntity accountEntity, BigDecimal transactionAmount);
    boolean isAccountCurrencyValid(AccountEntity accountEntity, AccountCurrency oppositeAccountCurrency);
    boolean isAccountBelongsToUser(Long accountId, String userName);
    AccountEntity findAccountById(Long accountId);
    AccountEntity findAccountByNumber(String accountNumber);
    void saveAccountEntity(AccountEntity accountEntity);
}
