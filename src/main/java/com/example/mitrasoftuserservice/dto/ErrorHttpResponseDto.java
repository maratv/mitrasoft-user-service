package com.example.mitrasoftuserservice.dto;

import java.time.LocalDateTime;

public record ErrorHttpResponseDto(
        int Status,
        String message,
        LocalDateTime timestamp) {
}

