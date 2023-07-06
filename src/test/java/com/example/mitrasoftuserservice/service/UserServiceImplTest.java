package com.example.mitrasoftuserservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.dto.UserDto;
import com.example.mitrasoftuserservice.exception.SourceNotFoundException;
import com.example.mitrasoftuserservice.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testGetUserAuthDtoByEmail() {
        User user = new User();
        user.setBirthday(LocalDate.of(1992, 2, 3));
        user.setEmail("petr@bk.ru");
        user.setFirstName("Petr");
        user.setId(1L);
        user.setLastName("Petrov");
        user.setPassword("pass");
        user.setRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);
        UserAuthDto actualUserAuthDtoByEmail = userServiceImpl.getUserAuthDtoByEmail("petr@bk.ru");
        assertEquals("petr@bk.ru", actualUserAuthDtoByEmail.getEmail());
        assertEquals(UserRole.ADMIN, actualUserAuthDtoByEmail.getRole());
        assertEquals("pass", actualUserAuthDtoByEmail.getPassword());
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    @Test
    void testGetUserAuthDtoByEmail2() {
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(SourceNotFoundException.class, () -> userServiceImpl.getUserAuthDtoByEmail("petr@bk.ru"));
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }


    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    void testGetAllUsers2() {
        User user = new User();
        user.setBirthday(LocalDate.of(1992, 2, 3));
        user.setEmail("petr@bk.ru");
        user.setFirstName("Petr");
        user.setId(1L);
        user.setLastName("Petrov");
        user.setPassword("pass");
        user.setRole(UserRole.ADMIN);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(1, userServiceImpl.getAllUsers().size());
        verify(userRepository).findAll();
    }

    @Test
    void testGetUserByEmail() {
        User user = new User();
        user.setBirthday(LocalDate.of(1992, 2, 3));
        user.setEmail("petr@bk.ru");
        user.setFirstName("Petr");
        user.setId(1L);
        user.setLastName("Petrov");
        user.setPassword("pass");
        user.setRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);
        User actualUserByEmail = userServiceImpl.getUserByEmail("petr@bk.ru");
        assertSame(user, actualUserByEmail);
        assertEquals("***", actualUserByEmail.getPassword());
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    @Test
    void testGetUserByEmail2() {
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(SourceNotFoundException.class, () -> userServiceImpl.getUserByEmail("petr@bk.ru"));
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    @Test
    void testGetUserByEmail3() {
        when(userRepository.getUserByEmail(Mockito.<String>any()))
                .thenThrow(new SourceNotFoundException("An error occurred"));
        assertThrows(SourceNotFoundException.class, () -> userServiceImpl.getUserByEmail("petr@bk.ru"));
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setBirthday(LocalDate.of(1992, 2, 3));
        user.setEmail("petr@bk.ru");
        user.setFirstName("Petr");
        user.setId(1L);
        user.setLastName("Petrov");
        user.setPassword("pass");
        user.setRole(UserRole.ADMIN);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        UserDto userDto = new UserDto();
        userDto.setBirthday(LocalDate.of(1992, 2, 3));
        userDto.setEmail("petr@bk.ru");
        userDto.setFirstName("Petr");
        userDto.setLastName("Petrov");
        userDto.setPassword("pass");
        userDto.setRole(UserRole.ADMIN);
        User actualCreateUserResult = userServiceImpl.createUser(userDto);
        assertEquals("1992-02-03", actualCreateUserResult.getBirthday().toString());
        assertEquals(UserRole.ADMIN, actualCreateUserResult.getRole());
        assertEquals("***", actualCreateUserResult.getPassword());
        assertEquals("Petrov", actualCreateUserResult.getLastName());
        assertEquals("Petr", actualCreateUserResult.getFirstName());
        assertEquals("petr@bk.ru", actualCreateUserResult.getEmail());
        verify(userRepository).save(Mockito.<User>any());
    }


    @Test
    void testDeleteUser() {
        User user = new User();
        user.setBirthday(LocalDate.of(1992, 2, 3));
        user.setEmail("petr@bk.ru");
        user.setFirstName("Petr");
        user.setId(1L);
        user.setLastName("Petrov");
        user.setPassword("pass");
        user.setRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepository).delete(Mockito.<User>any());
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);
        userServiceImpl.deleteUser("petr@bk.ru");
        verify(userRepository).getUserByEmail(Mockito.<String>any());
        verify(userRepository).delete(Mockito.<User>any());
    }

    @Test
    void testEditUser() {
        User user = new User();
        user.setBirthday(LocalDate.of(1992, 2, 3));
        user.setEmail("petr@bk.ru");
        user.setFirstName("Petr");
        user.setId(1L);
        user.setLastName("Petrov");
        user.setPassword("pass");
        user.setRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setBirthday(LocalDate.of(1992, 2, 3));
        user2.setEmail("petr@bk.ru");
        user2.setFirstName("Petr");
        user2.setId(1L);
        user2.setLastName("Petrov");
        user2.setPassword("pass");
        user2.setRole(UserRole.ADMIN);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);

        UserDto userDto = new UserDto();
        userDto.setBirthday(LocalDate.of(1992, 2, 3));
        userDto.setEmail("petr@bk.ru");
        userDto.setFirstName("Petr");
        userDto.setLastName("Petrov");
        userDto.setPassword("pass");
        userDto.setRole(UserRole.ADMIN);
        User actualEditUserResult = userServiceImpl.editUser("petr@bk.ru", userDto);
        assertSame(user, actualEditUserResult);
        assertEquals("1992-02-03", actualEditUserResult.getBirthday().toString());
        assertEquals(UserRole.ADMIN, actualEditUserResult.getRole());
        assertEquals("***", actualEditUserResult.getPassword());
        assertEquals("Petrov", actualEditUserResult.getLastName());
        assertEquals("Petr", actualEditUserResult.getFirstName());
        assertEquals("petr@bk.ru", actualEditUserResult.getEmail());
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    @Test
    void testChangeRole() {
        User user = new User();
        user.setBirthday(LocalDate.of(1992, 2, 3));
        user.setEmail("petr@bk.ru");
        user.setFirstName("Petr");
        user.setId(1L);
        user.setLastName("Petrov");
        user.setPassword("pass");
        user.setRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setBirthday(LocalDate.of(1992, 2, 3));
        user2.setEmail("petr@bk.ru");
        user2.setFirstName("Petr");
        user2.setId(1L);
        user2.setLastName("Petrov");
        user2.setPassword("pass");
        user2.setRole(UserRole.ADMIN);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);
        User actualChangeRoleResult = userServiceImpl.changeRole("petr@bk.ru", UserRole.ADMIN);
        assertSame(user, actualChangeRoleResult);
        assertEquals(UserRole.ADMIN, actualChangeRoleResult.getRole());
        assertEquals("***", actualChangeRoleResult.getPassword());
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }
}