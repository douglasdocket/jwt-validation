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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClienteOutput{");
        sb.append("nomeFantasia='").append(nomeFantasia).append('\'');
        sb.append(", razaoSocial='").append(razaoSocial).append('\'');
        sb.append(", cnpj='").append(cnpj).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
