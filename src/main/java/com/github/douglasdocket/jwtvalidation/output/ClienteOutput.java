package com.github.douglasdocket.jwtvalidation.output;

public class ClienteOutput {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;

    public ClienteOutput(String nomeFantasia, String razaoSocial, String cnpj) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }
}
