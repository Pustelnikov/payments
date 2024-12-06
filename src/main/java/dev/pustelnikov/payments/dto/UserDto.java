package dev.pustelnikov.payments.dto;

import dev.pustelnikov.payments.model.UserRole;
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
    @ToString.Exclude
    private Set<UserRole> userRoles;
    @ToString.Exclude
    private List<AccountDto> userAccounts;
}