package com.crm.controller;

import com.crm.dto.UserUpdateRequest;
import java.util.List;
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

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {

        return new ApiResponse<>(
                true,
                "Users Fetched Successfully",
                userService.getAllUsers()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(
            @PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "User Found",
                userService.getUserById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest request) {

        return new ApiResponse<>(
                true,
                "User Updated Successfully",
                userService.updateUser(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return new ApiResponse<>(
                true,
                "User Deleted Successfully",
                null
        );
    }
}