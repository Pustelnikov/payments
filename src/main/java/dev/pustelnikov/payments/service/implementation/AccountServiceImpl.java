package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.dto.account.AccountDto;
import dev.pustelnikov.payments.dto.account.AccountRegistrationRequestDto;
import dev.pustelnikov.payments.mapper.AccountMapper;
import dev.pustelnikov.payments.model.AccountStatus;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import dev.pustelnikov.payments.model.entity.UserEntity;
import dev.pustelnikov.payments.repository.AccountRepo;
import dev.pustelnikov.payments.service.AccountService;
import dev.pustelnikov.payments.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final AccountMapper accountMapper;
    private final UserService userService;

    @Override
    public List<AccountDto> getUserAccounts(String userName) {
        return accountMapper.mapToDto(accountRepo.findAccountsByUserName(userName));
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder tempAccountNumber;
        String accountNumber;
        tempAccountNumber = new StringBuilder();
        tempAccountNumber.append("AccNo");
        for (int i = 0; i < 14; i++) {
            int digit = random.nextInt(10);
            tempAccountNumber.append(digit);
        }
        accountNumber = tempAccountNumber.toString();
        return accountNumber;
    }

    @Override
    @Transactional
    public void registerAccount(AccountRegistrationRequestDto accountRegistrationRequestDto) {
        UserEntity userEntity = userService.findUserByUserName(accountRegistrationRequestDto.getUserName());
        AccountEntity accountEntity = AccountEntity.builder()
                .accountNumber(this.generateAccountNumber())
                .accountCurrency(accountRegistrationRequestDto.getAccountCurrency())
                .accountBalance(BigDecimal.valueOf(0))
                .accountStatus(AccountStatus.ACTIVE)
                .user(userEntity)
                .build();
        accountRepo.save(accountEntity);
    }
}