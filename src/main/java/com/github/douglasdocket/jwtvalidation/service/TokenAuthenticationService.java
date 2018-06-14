package com.github.douglasdocket.jwtvalidation.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.EXPIRATION_TIME;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.HEADER_STRING;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.SECRET;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.TOKEN_PREFIX;

public class TokenAuthenticationService {

    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        String token = TOKEN_PREFIX + "web-" + JWT;
        res.addHeader(HEADER_STRING, token);
    }

    public static Authentication getByToken(String token) {
        String user = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return user != null ? new UsernamePasswordAuthenticationToken(user, null, null) : null;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            return getByToken(token);
        }
        return null;
    }
}
