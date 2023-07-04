package com.example.mitrasoftuserservice.service;

import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.exception.DataNotFoundException;
import com.example.mitrasoftuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> {
                    u.setPassword("***");
                    return u;
                })
                .toList();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserAuthDto getUserAuthDtoByEmail(String email) {

        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserAuthDto userAuthDto = UserAuthDto.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
            return userAuthDto;
        } else {
            throw new DataNotFoundException("user with email '" + email + "' not found");
        }
    }


}
