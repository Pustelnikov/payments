package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.model.AccountCurrency;
import dev.pustelnikov.payments.model.AccountStatus;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import dev.pustelnikov.payments.service.AccountCheckService;
import dev.pustelnikov.payments.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountCheckServiceImpl implements AccountCheckService {

    private final UserService userService;

    @Override
    public boolean isAccountActive(AccountEntity accountEntity) {
        return accountEntity.getAccountStatus() == AccountStatus.ACTIVE;
    }

    @Override
    public boolean isAccountBalanceValid(AccountEntity accountEntity, BigDecimal transactionAmount) {
        BigDecimal accountEntityBalance = accountEntity.getAccountBalance();
        return accountEntityBalance.compareTo(transactionAmount) >= 0;
    }

    @Override
    public boolean isAccountCurrencyValid(AccountEntity accountEntity, AccountCurrency oppositeAccountCurrency) {
        return accountEntity.getAccountCurrency() == oppositeAccountCurrency;
    }

    @Override
    public boolean isAccountBelongsToUser(Long accountId, String userName) {
        return userService.findUserByUserName(userName).getUserAccounts()
                .stream().map(AccountEntity::getAccountId).toList().contains(accountId);
    }
}
