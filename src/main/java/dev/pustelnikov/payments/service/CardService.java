package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.card.CardDto;
import dev.pustelnikov.payments.dto.card.CardRegistrationRequestDto;
import java.util.List;

public interface CardService {
    void registerCard(CardRegistrationRequestDto cardRegistrationRequestDto);
    List<CardDto> getCardList();
}
