package com.example.mitrasoftuserservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                .formLogin(form -> form
                        .loginPage("/v1.0/auth/login")
                        //  .successForwardUrl("/")
                        .defaultSuccessUrl("/")
                        .failureUrl("/v1.0/auth/login-error")
                )


                .logout(logout -> logout
                        .logoutSuccessUrl("/v1.0/auth/login"))

                .authorizeHttpRequests(requests -> requests
                        //  .requestMatchers("/", "/home", "/reg").permitAll()
                        .requestMatchers("/", "/home", "/v1.0/auth/login", "/v1.0/auth/reg").permitAll()
                        .requestMatchers("/v1.0/admin/**").hasAuthority("ADMIN")
                        //       .requestMatchers("/v1.0/admin/**").hasAuthority("ADMIN")

                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/shared/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated())

                .exceptionHandling(exception ->
                        exception.accessDeniedPage("/403"));

        return http.build();
    }


 */


}
