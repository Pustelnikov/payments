package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.dto.card.CardDto;
import dev.pustelnikov.payments.dto.card.CardRegistrationRequestDto;
import dev.pustelnikov.payments.exception.account.AccountLockedException;
import dev.pustelnikov.payments.exception.card.CardAlreadyExistException;
import dev.pustelnikov.payments.mapper.CardMapper;
import dev.pustelnikov.payments.model.CardStatus;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import dev.pustelnikov.payments.model.entity.CardEntity;
import dev.pustelnikov.payments.repository.CardRepo;
import dev.pustelnikov.payments.service.AccountService;
import dev.pustelnikov.payments.service.CardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepo cardRepo;
    private final CardMapper cardMapper;
    private final AccountService accountService;

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder tempCardNumber;
        String cardNumber;
        tempCardNumber = new StringBuilder();
        tempCardNumber.append("Card");
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            tempCardNumber.append(digit);
        }
        cardNumber = tempCardNumber.toString();
        return cardNumber;
    }

    private Integer generateCardCvv() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    @Override
    @Transactional
    public void registerCard(CardRegistrationRequestDto cardRegistrationRequestDto)
            throws AccountLockedException, CardAlreadyExistException {
        String unregisteredCardNumber = this.generateCardNumber();
        Long accountEntityId = cardRegistrationRequestDto.getAccountId();
        AccountEntity accountEntity = accountService.findAccountById(accountEntityId);
        if (cardRepo.findByCardNumber(unregisteredCardNumber).isPresent()) {
            throw new CardAlreadyExistException("Card number %s already exist".formatted(unregisteredCardNumber));
        }
        if (!accountService.isAccountActive(accountEntity)) {
            throw new AccountLockedException("Account %s is not active".formatted(accountEntity.getAccountNumber()));
        }
        CardEntity cardEntity = CardEntity.builder()
                .cardNumber(unregisteredCardNumber)
                .cardExpirationDate(LocalDate.now().plusYears(3))
                .cardCvv(this.generateCardCvv())
                .cardStatus(CardStatus.ACTIVE)
                .account(accountEntity)
                .build();
        cardRepo.save(cardEntity);
    }

    @Override
    public List<CardDto> getCardList() {
        return cardMapper.mapToDto(cardRepo.findAll());
    }
}
