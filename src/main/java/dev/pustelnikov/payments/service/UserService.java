package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.dto.user.UserDto;
import dev.pustelnikov.payments.model.entity.UserEntity;
import java.util.List;

public interface UserService {
    void registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
    UserDto getUserInfo(String userName);
    List<UserDto> getUserList();
    UserEntity findUserById(Long userId);
    UserEntity findUserByUserName(String userName);
}
