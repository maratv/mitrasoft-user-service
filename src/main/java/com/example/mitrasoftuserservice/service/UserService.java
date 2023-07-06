package com.example.mitrasoftuserservice.service;

import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.dto.UserDto;

import java.util.List;

public interface UserService {

    UserAuthDto getUserAuthDtoByEmail(String email);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    User createUser(UserDto userDto);

    void deleteUser(String email);

    User editUser(String email, UserDto userDto);

    User changeRole(String email, UserRole userRole);

}
