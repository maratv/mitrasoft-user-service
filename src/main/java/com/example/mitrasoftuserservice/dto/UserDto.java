package com.example.mitrasoftuserservice.dto;

import com.example.mitrasoftuserservice.domain.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class UserDto {

    private String email;
    private UserRole role;
    private String password;

}
