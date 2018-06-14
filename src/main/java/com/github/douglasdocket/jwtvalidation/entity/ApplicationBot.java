package com.github.douglasdocket.jwtvalidation.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ApplicationBot {

    private String id = UUID.randomUUID().toString();

    @NotNull
    @NotBlank
    private String name;

    private String token;

    private Boolean active = Boolean.TRUE;

    public ApplicationBot() {

    }

    public ApplicationBot(String id, String name, String token, Boolean active) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApplicationBot{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
