package dev.pustelnikov.payments.dto;

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