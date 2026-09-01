package com.snapix.expensetracker.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
}
