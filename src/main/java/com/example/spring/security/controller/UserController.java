package com.example.spring.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.spring.security.common.EndpointConstants.INFO;
import static com.example.spring.security.common.EndpointConstants.USERS;
import static com.example.spring.security.common.RoleConstants.ROLE_USER;

@RestController()
@RequestMapping(USERS)
public class UserController {

    @GetMapping(INFO)
    @Secured({ROLE_USER})
    public String userInfo() {
        return "user info";
    }
}
