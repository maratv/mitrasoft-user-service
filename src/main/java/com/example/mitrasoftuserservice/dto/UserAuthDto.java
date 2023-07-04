package com.example.mitrasoftuserservice.dto;

import com.example.mitrasoftuserservice.domain.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthDto {

    private String email;
    private UserRole role;
    private String password;

}
