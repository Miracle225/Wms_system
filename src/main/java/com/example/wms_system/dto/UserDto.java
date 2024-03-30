package com.example.wms_system.dto;

import com.example.wms_system.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.wms_system.models.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    private static final String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

    @Email(regexp = EMAIL_PATTERN, message = "email must be valid")
    private String email;
    @Pattern(regexp = PASSWORD_PATTERN, message = """
            Password must contain:
            * At least 8 characters
            * At least 1 capital letter
            * At least 1 small letter
            * At least 1 number""")
    private String password;
    @NotNull(message = "Username must contain your name and surname")
    private String username;

    public User toUserEntity() {
        return new User(
                username,
                password,
                email,
                Role.ROLE_WAREHOUSE_MANAGER);
    }
}
