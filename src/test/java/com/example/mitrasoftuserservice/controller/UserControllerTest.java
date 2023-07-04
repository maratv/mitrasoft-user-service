package com.example.mitrasoftuserservice.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.dto.UserAuthDto;
import com.example.mitrasoftuserservice.service.UserService;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/get");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() throws Exception {
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/get");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail() throws Exception {
        User user = new User();
        user.setBirthday(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRole(UserRole.ADMIN);
        Optional<User> ofResult = Optional.of(user);
        when(userService.getUserByEmail(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/get/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"email\":\"jane.doe@example.org\",\"role\":\"ADMIN\",\"password\":\"***\",\"firstName\":\"Jane\",\"lastName\""
                                        + ":\"Doe\",\"birthday\":[1970,1,1]}"));
    }

    /**
     * Method under test: {@link UserController#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail2() throws Exception {
        when(userService.getUserByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/get/{email}",
                "jane.doe@example.org");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("user with email 'jane.doe@example.org' not found"));
    }

    /**
     * Method under test: {@link UserController#getUserDtoByEmail(String)}
     */
    @Test
    void testGetUserDtoByEmail() throws Exception {
        UserAuthDto userAuthDto = mock(UserAuthDto.class);
        when(userAuthDto.getRole()).thenReturn(UserRole.ADMIN);
        when(userAuthDto.getEmail()).thenReturn("jane.doe@example.org");
        when(userAuthDto.getPassword()).thenReturn("iloveyou");
        when(userService.getUserAuthDtoByEmail(Mockito.<String>any())).thenReturn(userAuthDto);
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#getUserDtoByEmail(String)}
     */
    @Test
    void testGetUserDtoByEmail2() throws Exception {
        UserAuthDto userAuthDto = mock(UserAuthDto.class);
        when(userAuthDto.getRole()).thenReturn(UserRole.ADMIN);
        when(userAuthDto.getEmail()).thenReturn("jane.doe@example.org");
        when(userAuthDto.getPassword()).thenReturn("iloveyou");
        when(userService.getUserAuthDtoByEmail(Mockito.<String>any())).thenReturn(userAuthDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/auth/{email}",
                "jane.doe@example.org");
        requestBuilder.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }
}

