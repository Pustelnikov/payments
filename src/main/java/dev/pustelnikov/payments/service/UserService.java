package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.model.entity.UserEntity;

public interface UserService {
    void registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
    UserEntity findUserByUserName(String userName);
}
