package com.github.douglasdocket.jwtvalidation.configuration;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationBot;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.EXPIRATION_TIME;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.SECRET;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.TOKEN_PREFIX;

@Configuration
public class ApplicationBotConfiguration {

    private Map<String, ApplicationBot> bots = new HashMap<>();

    @Bean
    public boolean initBots() {
        addBot("example-1-bot", true);
        addBot("example-2-bot", false);

        return true;
    }

    @Bean
    public Map<String, ApplicationBot> booooots() {
        return bots;
    }

    public void addBot(String name, Boolean active) {
        String id = newId();
        String token = newToken(id);

        bots.put(id, new ApplicationBot(id, name, token,  active));
    }

    private String newId() {
        return UUID.randomUUID().toString();
    }

    private String newToken(String id) {
        String JWT = Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        String token = TOKEN_PREFIX + "bot-" + JWT;

        return token;
    }

}
