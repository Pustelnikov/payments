package dev.pustelnikov.payments.dto.account;

import dev.pustelnikov.payments.model.AccountCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegistrationRequestDto {
    private String userName;
    private AccountCurrency accountCurrency;
}