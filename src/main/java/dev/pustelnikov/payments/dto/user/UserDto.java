package dev.pustelnikov.payments.dto.user;

import dev.pustelnikov.payments.dto.account.AccountDto;
import dev.pustelnikov.payments.model.UserRole;
import dev.pustelnikov.payments.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private String userPassword;
    private UserType userType;
    @ToString.Exclude
    private Set<UserRole> userRoles;
    @ToString.Exclude
    private List<AccountDto> userAccounts;
}