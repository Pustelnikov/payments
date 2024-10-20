package dev.pustelnikov.payments.dto.card;

import dev.pustelnikov.payments.dto.account.AccountDto;
import dev.pustelnikov.payments.model.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
        private Long cardId;
        private String cardNumber;
        private LocalDate cardExpirationDate;
        private Integer cardCvv;
        private CardStatus cardStatus;
        @ToString.Exclude
        private AccountDto account;
}