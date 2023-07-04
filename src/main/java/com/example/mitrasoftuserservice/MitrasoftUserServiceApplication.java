package com.example.mitrasoftuserservice;

import com.example.mitrasoftuserservice.domain.User;
import com.example.mitrasoftuserservice.domain.UserRole;
import com.example.mitrasoftuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class MitrasoftUserServiceApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(MitrasoftUserServiceApplication.class, args);
    }

    @PostConstruct
    public void init() {


        User user = User.builder()
                .email("404@bk.ru")
                .firstName("Marat")
                .lastName("Vakhitov")
                .password(passwordEncoder.encode("pass"))
                .birthday(LocalDate.of(1988, 2, 22))
                .role(UserRole.ADMIN)
                .build();

        userRepository.save(user);

        User user1 = User.builder()
                .email("kuza@bk.ru")
                .firstName("Tanya")
                .lastName("Vakhitova")
                .password(passwordEncoder.encode("pass1"))
                .birthday(LocalDate.of(1987, 7, 28))

                .role(UserRole.USER)
                .build();

        userRepository.save(user1);


    }


}
