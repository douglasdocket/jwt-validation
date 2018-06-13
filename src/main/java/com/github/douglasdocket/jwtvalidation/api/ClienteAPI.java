package com.github.douglasdocket.jwtvalidation.api;

import com.github.douglasdocket.jwtvalidation.output.ClienteOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteAPI {

    private ClienteOutput[] clienteOutputs = {
            new ClienteOutput("Sorvete Feliz", "Solange Sorvetes LTDA.", "00.967.149/0001-44"),
            new ClienteOutput("JJ Advocacia", "Juliano & Jadson Associados", "25.708.853/0001-03"),
            new ClienteOutput("Better Call Saul", "Saul Goodman Services", "64.329.231/0001-26")
    };

    @GetMapping
    public ClienteOutput[] listarClientes() {
        return clienteOutputs;
    }

}
