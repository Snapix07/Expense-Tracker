package com.snapix.expensetracker.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank @Size(min = 3, max = 32)
    private String username;
    @NotBlank @Size(min = 6)
    private String password;
    @NotBlank @Email
    private String email;
}
