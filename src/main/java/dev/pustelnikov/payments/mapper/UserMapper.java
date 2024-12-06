package dev.pustelnikov.payments.mapper;

import dev.pustelnikov.payments.dto.UserDto;
import dev.pustelnikov.payments.mapper.utility.CycleAvoidingMappingContext;
import dev.pustelnikov.payments.model.entity.UserEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CycleAvoidingMappingContext.class, AccountMapper.class})
public interface UserMapper {

    UserDto mapToDto(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity mapToEntity(UserDto userDto);

    List<UserDto> mapToDto(List<UserEntity> userEntityList);

    List<UserEntity> mapToEntity(List<UserDto> userDtoList);
}
