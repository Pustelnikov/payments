package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.account.AccountDto;
import dev.pustelnikov.payments.dto.account.AccountRegistrationRequestDto;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import java.util.List;

public interface AccountService {
    List<AccountDto> getUserAccounts(String userName);
    void registerAccount(AccountRegistrationRequestDto accountRegistrationRequestDto);
    AccountEntity findAccountById(Long accountId);
    void saveAccountEntity(AccountEntity accountEntity);
    AccountEntity findAccountByNumber(String accountNumber);
    AccountDto getAccountInfo(Long accountId);
    List<AccountDto> getAllAccounts();
    void lockAccount(Long accountId);
    void unlockAccount(Long accountId);
}
