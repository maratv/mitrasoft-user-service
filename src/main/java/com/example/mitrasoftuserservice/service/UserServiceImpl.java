package com.example.mitrasoftuserservice.service;

import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.dto.UserDto;
import com.example.mitrasoftuserservice.exception.SourceNotFoundException;
import com.example.mitrasoftuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserAuthDto getUserAuthDtoByEmail(String email) {

        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserAuthDto.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
        } else {
            throw new SourceNotFoundException("user with email '" + email + "' not found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .peek(u -> u.setPassword("***"))
                .toList();
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword("***");
            return user;
        } else {
            throw new SourceNotFoundException("user with email '" + email + "' not found");
        }
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        user.setPassword("***");
        return user;
    }

    @Override
    public void deleteUser(String email) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        optionalUser.ifPresent(userRepository::delete);
    }

    @Override
    public User editUser(String email, UserDto userDto) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(userDto.getEmail() != null ? userDto.getEmail() : user.getEmail());
            user.setRole(userDto.getRole() != null ? userDto.getRole() : user.getRole());
            user.setPassword(userDto.getPassword() != null ? userDto.getPassword() : user.getPassword());
            user.setFirstName(userDto.getFirstName() != null ? userDto.getFirstName() : user.getFirstName());
            user.setLastName(userDto.getLastName() != null ? userDto.getLastName() : user.getLastName());
            user.setBirthday(userDto.getBirthday() != null ? userDto.getBirthday() : user.getBirthday());
            userRepository.save(user);
            user.setPassword("***");
            return user;
        } else {
            throw new SourceNotFoundException("user with email '" + email + "' not found");
        }
    }

    @Override
    public User changeRole(String email, UserRole userRole) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(userRole);
            userRepository.save(user);
            user.setPassword("***");
            return user;
        } else {
            throw new SourceNotFoundException("user with email '" + email + "' not found");
        }
    }
}

