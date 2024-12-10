package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.AccountDto;
import java.util.List;

public interface AccountService {
    List<AccountDto> getUserAccounts(String userName);
}
