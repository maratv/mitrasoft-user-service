package com.example.mitrasoftuserservice.controller;

import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("auth/{email}")
    public UserAuthDto getUserDtoByEmail(@PathVariable String email) {
        return userService.getUserAuthDtoByEmail(email);
    }

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword("***");
            return ResponseEntity.ok(user);
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user with email '" + email + "' not found");
    }
}
