package dev.pustelnikov.payments.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositTransactionRequestDto {
    private Long accountId;
    private BigDecimal transactionAmount;
}