package dev.pustelnikov.payments.mapper;

import dev.pustelnikov.payments.dto.card.CardDto;
import dev.pustelnikov.payments.mapper.utility.CycleAvoidingMappingContext;
import dev.pustelnikov.payments.model.entity.CardEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CycleAvoidingMappingContext.class})
public interface CardMapper {

    CardDto mapToDto(CardEntity cardEntity);

    @InheritInverseConfiguration
    CardEntity mapToEntity(CardDto cardDto);

    List<CardDto> mapToDto(List<CardEntity> cardEntityList);

    List<CardEntity> mapToEntity(List<CardDto> cardDtoList);
}
