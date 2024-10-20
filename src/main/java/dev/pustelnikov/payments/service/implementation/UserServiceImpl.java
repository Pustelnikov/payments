package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.dto.user.UserDto;
import dev.pustelnikov.payments.exception.user.UserAlreadyExistsException;
import dev.pustelnikov.payments.exception.user.UserNotFoundException;
import dev.pustelnikov.payments.mapper.UserMapper;
import dev.pustelnikov.payments.model.UserRole;
import dev.pustelnikov.payments.model.entity.UserEntity;
import dev.pustelnikov.payments.repository.UserRepo;
import dev.pustelnikov.payments.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void registerUser(UserRegistrationRequestDto userRegistrationRequestDto) throws UserAlreadyExistsException {
        String unregisteredUserName = userRegistrationRequestDto.getUserName();
        if (userRepo.findByUserName(unregisteredUserName).isPresent()) {
            throw new UserAlreadyExistsException("User with username %s already exists".formatted(unregisteredUserName));
        }
        UserEntity userEntity = UserEntity.builder()
                .userName(unregisteredUserName)
                .userPassword(bCryptPasswordEncoder.encode(userRegistrationRequestDto.getUserPassword()))
                .userType(userRegistrationRequestDto.getUserType())
                .userRoles(Set.of(UserRole.ROLE_CUSTOMER))
                .build();
        userRepo.save(userEntity);
    }

    @Override
    public UserDto getUserInfo(String userName) {
        return userMapper.mapToDto(this.findUserByUserName(userName));
    }

    @Override
    public List<UserDto> getUserList() {
        return userMapper.mapToDto(userRepo.findAll());
    }

    @Override
    public UserEntity findUserById(Long userId) throws UserNotFoundException {
        return userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id %d not found".formatted(userId)));
    }

    @Override
    public UserEntity findUserByUserName(String userName) throws UserNotFoundException {
        return userRepo.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException("User with username %s not found".formatted(userName)));
    }
}
