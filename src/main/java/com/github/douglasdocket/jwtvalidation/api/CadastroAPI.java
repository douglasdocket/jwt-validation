package com.github.douglasdocket.jwtvalidation.api;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationUser;
import com.github.douglasdocket.jwtvalidation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cadastrar")
public class CadastroAPI {

    @Autowired
    private UserService userService;

    @PostMapping
    public void cadastrarNovoUsuario(@RequestBody @Validated ApplicationUser applicationUser) {
        userService.cadastrarUsuario(applicationUser);
    }

}
