package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.account.AccountDto;
import dev.pustelnikov.payments.dto.account.AccountRegistrationRequestDto;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import java.util.List;

public interface AccountService {
    List<AccountDto> getUserAccounts(String userName);
    void registerAccount(AccountRegistrationRequestDto accountRegistrationRequestDto);
    AccountEntity findAccountById(Long accountId);
    boolean isAccountActive(AccountEntity accountEntity);
    void saveAccountEntity(AccountEntity accountEntity);
}
