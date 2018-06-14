package com.github.douglasdocket.jwtvalidation.configuration;

import com.github.douglasdocket.jwtvalidation.output.ClienteOutput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClienteAPIConfiguration {

    private List<ClienteOutput> clientes = new ArrayList<>();

    @Bean
    public boolean initClientes() {
        addCliente("Sorvete Feliz", "Solange Sorvetes LTDA.", "00.967.149/0001-44");
        addCliente("JJ Advocacia", "Juliano & Jadson Associados", "25.708.853/0001-03");
        addCliente("Better Call Saul", "Saul Goodman Services", "64.329.231/0001-26");

        return true;
    }

    @Bean
    public List<ClienteOutput> clientes() {
        return clientes;
    }

    public void addCliente(String nomeFantasia, String razaoSocial, String cnpj) {
        clientes.add(new ClienteOutput(nomeFantasia, razaoSocial, cnpj));
    }

}
