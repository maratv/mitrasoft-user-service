package com.example.mitrasoftuserservice.service;

import com.example.mitrasoftuserservice.dto.UserDto;
import com.example.mitrasoftuserservice.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserByEmail(String email);

    UserDto getUserDtoByEmail(String email);

    String getUserRole(UserDto userDto);

}
