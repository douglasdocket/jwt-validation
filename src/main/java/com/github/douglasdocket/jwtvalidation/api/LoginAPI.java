package com.github.douglasdocket.jwtvalidation.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class LoginAPI {

    @PostMapping("tiranu")
    public void test() {
        System.out.println("LoginAPI.test");
    }

}
