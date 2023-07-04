package com.example.mitrasoftuserservice.service;

import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserByEmail(String email);

    UserAuthDto getUserAuthDtoByEmail(String email);



}
