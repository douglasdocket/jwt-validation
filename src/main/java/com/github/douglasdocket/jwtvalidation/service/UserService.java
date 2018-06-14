package com.github.douglasdocket.jwtvalidation.service;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Map<String, ApplicationUser> users;

    public Map<String, ApplicationUser> cadastrarUsuario(ApplicationUser applicationUser) {

        String username = applicationUser.getUsername();
        String rawPassword = applicationUser.getRawPassword();

        String encodedPassword = this.bCryptPasswordEncoder.encode(rawPassword);

        users.put(username, new ApplicationUser(username, encodedPassword));

        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ApplicationUser applicationUser = users.get(s);

        if (applicationUser == null) {
            throw new UsernameNotFoundException(s);
        }

        return new User(applicationUser.getUsername(), applicationUser.getRawPassword(), emptyList());
    }

}
