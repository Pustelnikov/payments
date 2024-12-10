package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.dto.AccountDto;
import dev.pustelnikov.payments.mapper.AccountMapper;
import dev.pustelnikov.payments.repository.AccountRepo;
import dev.pustelnikov.payments.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> getUserAccounts(String userName) {
        return accountMapper.mapToDto(accountRepo.findAccountsByUserName(userName));
    }
}
