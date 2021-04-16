package com.bude.zuulserver.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "login")
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "state")
    private Integer state;
}
