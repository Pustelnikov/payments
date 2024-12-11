package dev.pustelnikov.payments.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionRequestDto {
    private Long accountId;
    private String oppositeAccountNumber;
    private BigDecimal transactionAmount;
}