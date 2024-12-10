package dev.pustelnikov.payments.dto.account;

import dev.pustelnikov.payments.dto.transaction.TransactionDto;
import dev.pustelnikov.payments.dto.user.UserDto;
import dev.pustelnikov.payments.model.AccountCurrency;
import dev.pustelnikov.payments.model.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
        private Long accountId;
        private String accountNumber;
        private AccountCurrency accountCurrency;
        private BigDecimal accountBalance;
        private AccountStatus accountStatus;
        @ToString.Exclude
        private UserDto user;
        @ToString.Exclude
        private List<TransactionDto> accountTransactions;
}