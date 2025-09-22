package com.digital.diary.backend.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.digital.diary.backend.models.Users;
import com.digital.diary.backend.repositories.UserRepository;

@Component
public class UserInfoFromJwt {
    private final UserRepository userRepository;

    public UserInfoFromJwt(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getMineFromToken() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userRepository.findByEmail(userDetails.getUsername());
        return user;
    }
}
