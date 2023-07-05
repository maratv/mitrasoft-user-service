package com.example.mitrasoftuserservice.service;

import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserAuthDto getUserAuthDtoByEmail(String email);

    List<User> getAllUsers();

    Optional<User> getUserByEmail(String email);

    User createUser(UserDto userDto);

    Optional<User> deleteUser(String email);

    Optional<User> editUser(String email, UserDto userDto);

    Optional<User> changeRole(String email, UserRole userRole);

    UserRole getRole(String email);



}
