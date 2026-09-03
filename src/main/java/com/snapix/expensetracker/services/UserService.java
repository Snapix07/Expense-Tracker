package com.snapix.expensetracker.services;

import com.snapix.expensetracker.dto.auth.AuthResponseDTO;
import com.snapix.expensetracker.dto.user.UserRequestDTO;
import com.snapix.expensetracker.dto.user.UserResponseDTO;
import com.snapix.expensetracker.entity.User;
import com.snapix.expensetracker.exception.UserExistException;
import com.snapix.expensetracker.mapper.UserMapper;
import com.snapix.expensetracker.repository.UserRepository;
import com.snapix.expensetracker.security.JwtGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;


    public UserService(PasswordEncoder encoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

        public UserResponseDTO registerUser(UserRequestDTO userRequestDTO){
            User user = UserMapper.toEntity(userRequestDTO);
            if(userRepository.findByUsername(user.getUsername()).isPresent()){
                throw new UserExistException("That username exists");
            }
            user.setPassword(encoder.encode(userRequestDTO.getPassword()));
            userRepository.save(user);

            return UserMapper.toDto(user);
        }

        public AuthResponseDTO loginUser(UserRequestDTO userRequestDTO){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userRequestDTO.getUsername(),
                    userRequestDTO.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtGenerator.generateToken(authentication);
            return new AuthResponseDTO(jwt);
        }
}
