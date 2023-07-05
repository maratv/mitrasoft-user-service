package com.example.mitrasoftuserservice.controller;

import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.dto.UserDto;
import com.example.mitrasoftuserservice.exception.SourceNotFoundException;
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
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword("***");
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user with email '" + email + "' not found");
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        Optional<User> optionalUser = userService.deleteUser(email);
        if (optionalUser.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/update/{email}")
    public ResponseEntity<?> update(@PathVariable String email, @RequestBody UserDto userDto) {
        Optional<User> optionalUser = userService.editUser(email, userDto);
        return optionalUser
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new SourceNotFoundException("User with email '" + email + "' not found"));
    }

    @PostMapping("/role/{email}")
    public ResponseEntity<?> changeRole(@PathVariable String email, @RequestBody UserRole userRole) {
        Optional<User> optionalUser = userService.changeRole(email, userRole);
        return optionalUser
                .map(ResponseEntity::ok)
                .orElseThrow(() ->  new SourceNotFoundException("User with email '" + email + "' not found"));
    }

//    @GetMapping("/getrole/{email}")
//    public ResponseEntity<?> changeRole(@PathVariable String email) {
//        return ResponseEntity.ok(userService.getRole(email));
//    }


//    @ExceptionHandler(SourceNotFoundException.class)
//    public ResponseEntity<String> handleResourceNotFoundException(SourceNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
}
