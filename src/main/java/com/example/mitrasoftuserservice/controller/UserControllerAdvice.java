package com.example.mitrasoftuserservice.controller;

import com.example.mitrasoftuserservice.dto.ErrorHttpResponseDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class UserControllerAdvice {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorHttpResponseDto> handleRuntimeException(RuntimeException ex) {
        var errorHttpResponseDto = new ErrorHttpResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getLocalizedMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorHttpResponseDto);
    }

    @ExceptionHandler(value = {DataAccessException.class})
    public ResponseEntity<ErrorHttpResponseDto> handleDataAccessException(DataAccessException ex) {
        var errorHttpResponseDto = new ErrorHttpResponseDto(HttpStatus.BAD_REQUEST.value(),
                ex.getLocalizedMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorHttpResponseDto);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorHttpResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        var errorHttpResponseDto = new ErrorHttpResponseDto(HttpStatus.BAD_REQUEST.value(),
                ex.getLocalizedMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorHttpResponseDto);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ErrorHttpResponseDto> handleConstraintViolationException(ConstraintViolationException ex) {
        var errorHttpResponseDto = new ErrorHttpResponseDto(HttpStatus.BAD_REQUEST.value(),
                ex.getLocalizedMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorHttpResponseDto);
    }
}
