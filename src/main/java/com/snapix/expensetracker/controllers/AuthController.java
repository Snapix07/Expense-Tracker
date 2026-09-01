package com.snapix.expensetracker.controllers;

import com.snapix.expensetracker.dto.auth.AuthResponseDTO;
import com.snapix.expensetracker.dto.user.UserRequestDTO;
import com.snapix.expensetracker.dto.user.UserResponseDTO;
import com.snapix.expensetracker.entity.User;
import com.snapix.expensetracker.repository.UserRepository;
import com.snapix.expensetracker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO user = userService.registerUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.loginUser(userRequestDTO));
    }


}
