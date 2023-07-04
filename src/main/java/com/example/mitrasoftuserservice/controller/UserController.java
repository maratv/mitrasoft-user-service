package com.example.mitrasoftuserservice.controller;

import com.example.mitrasoftuserservice.dto.UserDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("g/{email}")
    String getUserDtoByEmail1(@PathVariable String email) {
        return userService.getUserDtoByEmail(email).toString();
    }

    @GetMapping("get/{email}")
    public UserDto getUserDtoByEmail(@PathVariable String email) {
        return userService.getUserDtoByEmail(email);
    }


    @GetMapping ("/ge")
    String getUserRole(@RequestBody UserDto userDto) {
        System.out.println(userDto.toString());
        return userService.getUserRole(userDto);
    }


}
