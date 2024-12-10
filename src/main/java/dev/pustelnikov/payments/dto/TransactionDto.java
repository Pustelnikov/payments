package dev.pustelnikov.payments.dto;

import dev.pustelnikov.payments.dto.account.AccountDto;
import dev.pustelnikov.payments.model.TransactionStatus;
import dev.pustelnikov.payments.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long transactionId;
    private String transactionUuid;
    private TransactionType transactionType;
    private String oppositeAccountNumber;
    private BigDecimal transactionAmount;
    private LocalDateTime transactionTimestamp;
    private TransactionStatus transactionStatus;
    @ToString.Exclude
    private AccountDto account;
}