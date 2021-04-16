package com.bude.zuulserver.model;


import lombok.Data;

@Data
public class LoginUser {

    private String username;

    private String password;

    private Integer rememberMe;
}
