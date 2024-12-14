package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.user.UserDto;
import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.model.entity.UserEntity;
import java.util.List;

public interface UserService {
    void registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
    UserEntity findUserByUserName(String userName);
    List<UserDto> getAllUsers();
}
