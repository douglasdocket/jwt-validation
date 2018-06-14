package com.github.douglasdocket.jwtvalidation.service;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationBot;
import com.github.douglasdocket.jwtvalidation.exception.BotNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.EXPIRATION_TIME;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.SECRET;
import static com.github.douglasdocket.jwtvalidation.hardcode.SecurityConstants.TOKEN_PREFIX;

@Service
public class BotService {

    @Autowired
    private Map<String, ApplicationBot> bots;

    public Map<String, ApplicationBot> cadastrarBot(ApplicationBot applicationBot) {
        String id = applicationBot.getId();
        String name = applicationBot.getName();
        String token = applicationBot.getToken();
        Boolean active = applicationBot.getActive();

        token = newToken(applicationBot);

        bots.put(id, new ApplicationBot(id, name, token, active));

        return bots;
    }

    public String newToken(ApplicationBot applicationBot) {
        String id = applicationBot.getId();

        String JWT = Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        String token = TOKEN_PREFIX + "bot-" + JWT;

        return token;
    }

    public ApplicationBot loadBotById(String s) throws BotNotFoundException {
        ApplicationBot applicationBot = bots.get(s);

        if (applicationBot == null) {
            throw new BotNotFoundException(s);
        }

        return applicationBot;
    }
}
