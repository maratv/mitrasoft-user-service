package com.example.mitrasoftuserservice.controller;

import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.dto.UserDto;
import com.example.mitrasoftuserservice.service.UserService;
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

    @GetMapping("/auth/{email}")
    public UserAuthDto getUserDtoByEmail(@PathVariable String email) {
        return userService.getUserAuthDtoByEmail(email);
    }

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/update/{email}")
    public ResponseEntity<?> update(@PathVariable String email, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.editUser(email, userDto));
    }

    @PostMapping("/role/{email}")
    public ResponseEntity<?> changeRole(@PathVariable String email, @RequestBody UserRole userRole) {
        return ResponseEntity.ok(userService.changeRole(email, userRole));

    }
}