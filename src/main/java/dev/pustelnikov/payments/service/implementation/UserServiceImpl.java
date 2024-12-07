package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.exception.UserAlreadyExistsException;
import dev.pustelnikov.payments.model.UserRole;
import dev.pustelnikov.payments.model.entity.UserEntity;
import dev.pustelnikov.payments.repository.UserRepo;
import dev.pustelnikov.payments.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    @Transactional
    public void registerUser(UserRegistrationRequestDto userRegistrationRequestDto) throws UserAlreadyExistsException {
        String unregisteredUserName = userRegistrationRequestDto.getUserName();
        if (userRepo.findByUserName(unregisteredUserName).isPresent()) {
            throw new UserAlreadyExistsException("User with username %s already exists".formatted(unregisteredUserName));
        }
        UserEntity userEntity = UserEntity.builder()
                .userName(unregisteredUserName)
                .userPassword(userRegistrationRequestDto.getUserPassword())
                .userRoles(Set.of(UserRole.ROLE_CUSTOMER))
                .build();
        userRepo.save(userEntity);
    }
}
