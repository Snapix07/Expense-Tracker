package com.snapix.expensetracker.dto.auth;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String jwt;
    private String tokenType = "Bearer";

    public AuthResponseDTO(String jwt){
        this.jwt = jwt;
    }
}
