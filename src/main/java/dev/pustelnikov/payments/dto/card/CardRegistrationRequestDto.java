package dev.pustelnikov.payments.dto.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRegistrationRequestDto {
    private Long accountId;
}