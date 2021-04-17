package com.bude.contestserver.model;


import lombok.Data;

@Data
public class Login {

    private Integer id;

    private String username;

    private String password;

    private String token;

    private String role;

    private String displayName;

    private String sign;

    private Integer state;
    
}
