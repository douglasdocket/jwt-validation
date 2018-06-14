package com.github.douglasdocket.jwtvalidation.api;

import com.github.douglasdocket.jwtvalidation.output.ClienteOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteAPI {

    @Autowired
    private List<ClienteOutput> clientes;

    @GetMapping
    public List<ClienteOutput> listarClientes(Authentication authentication) {
        return clientes;
    }

}
