package dev.pustelnikov.payments.mapper;

import dev.pustelnikov.payments.dto.transaction.TransactionDto;
import dev.pustelnikov.payments.mapper.utility.CycleAvoidingMappingContext;
import dev.pustelnikov.payments.model.entity.TransactionEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CycleAvoidingMappingContext.class})
public interface TransactionMapper {

    TransactionDto mapToDto(TransactionEntity transactionEntity);

    @InheritInverseConfiguration
    TransactionEntity mapToEntity(TransactionDto transactionDto);

    List<TransactionDto> mapToDto(List<TransactionEntity> transactionEntityList);

    List<TransactionEntity> mapToEntity(List<TransactionDto> transactionDtoList);
}
