package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.account.AccountDto;
import dev.pustelnikov.payments.dto.account.AccountRegistrationRequestDto;
import dev.pustelnikov.payments.model.AccountCurrency;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<AccountDto> getUserAccounts(String userName);
    void registerAccount(AccountRegistrationRequestDto accountRegistrationRequestDto);
    AccountEntity findAccountById(Long accountId);
    boolean isAccountActive(AccountEntity accountEntity);
    void saveAccountEntity(AccountEntity accountEntity);
    boolean isAccountBalanceValid(AccountEntity accountEntity, BigDecimal transactionAmount);
    AccountEntity findAccountByNumber(String accountNumber);
    boolean isAccountCurrencyValid(AccountEntity accountEntity, AccountCurrency oppositeAccountCurrency);
    AccountDto getAccountInfo(Long accountId);
    List<AccountDto> getAllAccounts();
}
