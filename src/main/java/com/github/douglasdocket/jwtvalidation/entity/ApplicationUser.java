package com.github.douglasdocket.jwtvalidation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ApplicationUser {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @JsonProperty("password")
    private String rawPassword;

    public ApplicationUser() {

    }

    public ApplicationUser(String username, String rawPassword) {
        this.username = username;
        this.rawPassword = rawPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApplicationUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", rawPassword='").append(rawPassword).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
