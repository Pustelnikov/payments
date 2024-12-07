package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;

public interface UserService {
    void registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
