package dev.pustelnikov.payments.dto.transaction;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawTransactionRequestDto {
    @NotNull
    private Long accountId;
    @DecimalMin(value = "0", inclusive = false, message = "Transaction amount must be greater than 0")
    private BigDecimal transactionAmount;
}