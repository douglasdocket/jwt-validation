package com.github.douglasdocket.jwtvalidation.configuration;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationUserConfiguration {

    private Map<String, ApplicationUser> users = new HashMap<>();

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public boolean initUsers() {
        addUser("doug", "123");

        return true;
    }

    @Bean
    public Map<String, ApplicationUser> users() {
        return users;
    }

    public void addUser(String username, String rawPassword) {
        String encodedPassword = bCryptPasswordEncoder().encode(rawPassword);

        users.put(username, new ApplicationUser(username, encodedPassword));
    }

}
