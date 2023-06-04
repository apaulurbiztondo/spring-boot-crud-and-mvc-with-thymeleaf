package com.springboot.thymeleafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.springboot.thymeleafdemo.constants.Constants.ADMIN_ROLE;
import static com.springboot.thymeleafdemo.constants.Constants.EMPLOYEE_ROLE;
import static com.springboot.thymeleafdemo.constants.Constants.MANAGER_ROLE;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles(EMPLOYEE_ROLE)
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles(EMPLOYEE_ROLE, MANAGER_ROLE)
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles(EMPLOYEE_ROLE, MANAGER_ROLE, ADMIN_ROLE)
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config -> config
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                );
        return http.build();
    }
}
