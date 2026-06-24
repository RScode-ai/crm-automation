package com.crm.controller;

import com.crm.dto.ApiResponse;
import com.crm.dto.LoginRequest;
import com.crm.dto.LoginResponse;
import com.crm.dto.UserRequest;
import com.crm.entity.User;
import com.crm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<User> registerUser(
            @Valid @RequestBody UserRequest request) {

        return new ApiResponse<>(
                true,
                "User Registered Successfully",
                userService.registerUser(request)
        );
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return new ApiResponse<>(
                true,
                "Login Success",
                userService.login(request)
        );
    }
}