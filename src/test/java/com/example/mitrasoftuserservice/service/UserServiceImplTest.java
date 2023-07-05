package com.example.mitrasoftuserservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
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

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() {
        User user = new User();
        user.setBirthday(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(UserRole.ADMIN);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(1, userServiceImpl.getAllUsers().size());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers3() {
        User user = new User();
        user.setBirthday(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(UserRole.ADMIN);

        User user2 = new User();
        user2.setBirthday(LocalDate.of(1970, 1, 1));
        user2.setEmail("john.smith@example.org");
        user2.setFirstName("John");
        user2.setId(2L);
        user2.setLastName("Smith");
        user2.setPassword("***");
        user2.setRole(UserRole.USER);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(2, userServiceImpl.getAllUsers().size());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers4() {
        when(userRepository.findAll()).thenThrow(new SourceNotFoundException("An error occurred"));
        assertThrows(SourceNotFoundException.class, () -> userServiceImpl.getAllUsers());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail() {
        User user = new User();
        user.setBirthday(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);
        Optional<User> actualUserByEmail = userServiceImpl.getUserByEmail("jane.doe@example.org");
        assertSame(ofResult, actualUserByEmail);
        assertTrue(actualUserByEmail.isPresent());
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail2() {
        when(userRepository.getUserByEmail(Mockito.<String>any()))
                .thenThrow(new SourceNotFoundException("An error occurred"));
        assertThrows(SourceNotFoundException.class, () -> userServiceImpl.getUserByEmail("jane.doe@example.org"));
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserAuthDtoByEmail(String)}
     */
    @Test
    void testGetUserAuthDtoByEmail() {
        User user = new User();
        user.setBirthday(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);
        UserAuthDto actualUserAuthDtoByEmail = userServiceImpl.getUserAuthDtoByEmail("jane.doe@example.org");
        assertEquals("jane.doe@example.org", actualUserAuthDtoByEmail.getEmail());
        assertEquals(UserRole.ADMIN, actualUserAuthDtoByEmail.getRole());
        assertEquals("iloveyou", actualUserAuthDtoByEmail.getPassword());
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserAuthDtoByEmail(String)}
     */
    @Test
    void testGetUserAuthDtoByEmail2() {
        when(userRepository.getUserByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(SourceNotFoundException.class, () -> userServiceImpl.getUserAuthDtoByEmail("jane.doe@example.org"));
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserAuthDtoByEmail(String)}
     */
    @Test
    void testGetUserAuthDtoByEmail3() {
        when(userRepository.getUserByEmail(Mockito.<String>any()))
                .thenThrow(new SourceNotFoundException("An error occurred"));
        assertThrows(SourceNotFoundException.class, () -> userServiceImpl.getUserAuthDtoByEmail("jane.doe@example.org"));
        verify(userRepository).getUserByEmail(Mockito.<String>any());
    }
}

