package com.example.mitrasoftuserservice.controller;

import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.dto.UserDto;
import com.example.mitrasoftuserservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Method for remote auth call",
            description = "Method for remote auth call")
    @GetMapping("/auth/{email}")
    public UserAuthDto getUserDtoByEmail(@PathVariable String email) {
        return userService.getUserAuthDtoByEmail(email);
    }

    @Operation(summary = "Get all users",
            description = "Get all users")
    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get user by email",
            description = "Get user by email")
    @GetMapping("/get/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @Operation(summary = "Create new user",
            description = "Create new user")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @Operation(summary = "Delete user",
            description = "Delete user")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Update user",
            description = "Update user")
    @PostMapping("/update/{email}")
    public ResponseEntity<?> update(@PathVariable String email, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.editUser(email, userDto));
    }

    @Operation(summary = "Change Role",
            description = "Change Role")
    @PostMapping("/role/{email}")
    public ResponseEntity<?> changeRole(@PathVariable String email, @RequestBody UserRole userRole) {
        return ResponseEntity.ok(userService.changeRole(email, userRole));
    }
}