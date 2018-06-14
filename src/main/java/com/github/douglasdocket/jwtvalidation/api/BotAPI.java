package com.github.douglasdocket.jwtvalidation.api;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bots")
public class BotAPI {

    @Autowired
    private Map<String, ApplicationBot> bots;

    @GetMapping
    public List<ApplicationBot> listarBots(Authentication authentication) {
        List<ApplicationBot> listBot = this.bots.values().stream().collect(Collectors.toList());

        return listBot;
    }

}
