package com.crm.service;

import com.crm.dto.LoginRequest;
import com.crm.dto.LoginResponse;
import com.crm.dto.UserRequest;
import com.crm.entity.User;
import com.crm.exception.UserAlreadyExistsException;
import com.crm.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public User registerUser(
            UserRequest request) {

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new UserAlreadyExistsException(
                    "Email already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                ) // JWT phase me encrypt karenge
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }



    public LoginResponse login(
            LoginRequest request) {

        User user = userRepository.findByEmail(
                        request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid Email"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole().name()
                );

        return new LoginResponse(token);
    }
}