package dev.pustelnikov.payments.dto.transaction;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionRequestDto {
    @NotNull
    private Long accountId;
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Please, enter recipient's account properly")
    private String oppositeAccountNumber;
    @DecimalMin(value = "0", inclusive = false, message = "Transaction amount must be greater than 0")
    private BigDecimal transactionAmount;
}