package com.example.mitrasoftuserservice.dto;

import com.example.mitrasoftuserservice.domain.UserRole;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String email;

    private UserRole role;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate birthday;
}
