package dev.pustelnikov.payments.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestDto {
    @Pattern(regexp = "^[A-Za-z0-9]+(?:[_-]?[A-Za-z0-9]+)*$", message = "Username must contain only ASCII letters, digits, hyphens and underscores")
    private String userName;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must contain minimum eight characters, at least one letter and one number")
    private String userPassword;
}