package com.github.douglasdocket.jwtvalidation.api;

import com.github.douglasdocket.jwtvalidation.entity.ApplicationUser;
import com.github.douglasdocket.jwtvalidation.output.ClienteOutput;
import com.github.douglasdocket.jwtvalidation.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cadastrar")
public class CadastroAPI {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public void cadastrarNovoUsuario(@RequestBody @Validated ApplicationUser applicationUser) {
        usuarioService.cadastrarUsuario(applicationUser);
    }

}
