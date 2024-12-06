package dev.pustelnikov.payments.mapper;

import dev.pustelnikov.payments.dto.AccountDto;
import dev.pustelnikov.payments.mapper.utility.CycleAvoidingMappingContext;
import dev.pustelnikov.payments.model.entity.AccountEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CycleAvoidingMappingContext.class, TransactionMapper.class})
public interface AccountMapper {

    AccountDto mapToDto(AccountEntity accountEntity);

    @InheritInverseConfiguration
    AccountEntity mapToEntity(AccountDto accountDto);

    List<AccountDto> mapToDto(List<AccountEntity> accountEntityList);

    List<AccountEntity> mapToEntity(List<AccountDto> accountDtoList);
}
