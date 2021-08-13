package com.example.service;

import com.example.dto.UserDto;
import com.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends GeneralService<User>, UserDetailsService {
    Optional<User> findByEmail(String username);
}
