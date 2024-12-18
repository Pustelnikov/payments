package dev.pustelnikov.payments.service.implementation;

import dev.pustelnikov.payments.dto.user.UserDto;
import dev.pustelnikov.payments.dto.user.UserRegistrationRequestDto;
import dev.pustelnikov.payments.exception.user.UserAlreadyExistsException;
import dev.pustelnikov.payments.exception.user.UserNotFoundException;
import dev.pustelnikov.payments.mapper.UserMapper;
import dev.pustelnikov.payments.model.UserRole;
import dev.pustelnikov.payments.model.entity.UserEntity;
import dev.pustelnikov.payments.repository.UserRepo;
import dev.pustelnikov.payments.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
                .userRoles(Set.of(UserRole.ROLE_CUSTOMER))
                .build();
        userRepo.save(userEntity);
    }

    @Override
    public UserEntity findUserByUserName(String userName) throws UserNotFoundException {
        return userRepo.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException("User with username %s not found".formatted(userName)));
    }

    @Override
    public Page<UserDto> getAllUsers(String searchKeyword, Integer pageNumber, Integer pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        if (searchKeyword == null) {
            return userRepo.findAll(pageable).map(userMapper::mapToDto);
        } else {
            return userRepo.search(searchKeyword, pageable).map(userMapper::mapToDto);
        }
    }
}
