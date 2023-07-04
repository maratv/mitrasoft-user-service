package com.example.mitrasoftuserservice.service;

import com.example.mitrasoftuserservice.dto.UserDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {

        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDto userDto = UserDto.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
            return userDto;
        } else {
            return null;
        }
    }

    @Override
    public String getUserRole(UserDto userDto) {
        Optional<User> optionalUser = userRepository.getUserByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            if (Objects.equals(userDto.getPassword(), optionalUser.get().getPassword())) {
                return optionalUser.get().getRole().toString();
            } else {
                return "incorrect password";
            }
        }
        return "user not found";

    }


}
