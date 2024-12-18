package dev.pustelnikov.payments.service;

import dev.pustelnikov.payments.dto.user.UserDto;
import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.model.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface UserService {
    void registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
    UserEntity findUserByUserName(String userName);
    Page<UserDto> getAllUsers(String searchKeyword, Integer pageNumber, Integer pageSize, String sortField, String sortDirection);}
