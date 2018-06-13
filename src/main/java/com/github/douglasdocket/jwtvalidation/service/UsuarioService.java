package com.github.douglasdocket.jwtvalidation.service;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyList;

@Service
public class UsuarioService implements UserDetailsService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String, ApplicationUser> usuarios = new HashMap<>();

    public Map<String, ApplicationUser> cadastrarUsuario(ApplicationUser applicationUser) {

        String username = applicationUser.getUsername();
        String rawPassword = applicationUser.getRawPassword();

        String encodedPassword = this.bCryptPasswordEncoder.encode(rawPassword);

        usuarios.put(username, new ApplicationUser(username, encodedPassword));

        return usuarios;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ApplicationUser applicationUser = usuarios.get(s);

        if (applicationUser == null) {
            throw new UsernameNotFoundException(s);
        }

        return new User(applicationUser.getUsername(), applicationUser.getRawPassword(), emptyList());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        if (bCryptPasswordEncoder != null) {
            return bCryptPasswordEncoder;
        }

        bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder;
    }

}
