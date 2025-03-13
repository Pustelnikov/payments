package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.model.AccountCurrency;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import java.math.BigDecimal;

public interface AccountCheckService {
    boolean isAccountActive(AccountEntity accountEntity);
    boolean isAccountBalanceValid(AccountEntity accountEntity, BigDecimal transactionAmount);
    boolean isAccountCurrencyValid(AccountEntity accountEntity, AccountCurrency oppositeAccountCurrency);
    boolean isAccountBelongsToUser(Long accountId, String userName);
}
