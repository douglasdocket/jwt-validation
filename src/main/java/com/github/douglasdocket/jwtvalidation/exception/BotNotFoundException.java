package com.github.douglasdocket.jwtvalidation.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BotNotFoundException extends UsernameNotFoundException {
    public BotNotFoundException(String msg) {
        super(msg);
    }
}
