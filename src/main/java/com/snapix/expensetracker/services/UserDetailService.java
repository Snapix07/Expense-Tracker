package com.snapix.expensetracker.services;

import com.snapix.expensetracker.security.CustomUserDetails;
import com.snapix.expensetracker.entity.User;
import com.snapix.expensetracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return new CustomUserDetails(user.orElseThrow(()->new UsernameNotFoundException(username)));
    }
}
