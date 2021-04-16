package com.bude.zuulserver.controller;

import com.alibaba.fastjson.JSON;
import com.bude.zuulserver.entity.LoginEntity;
import com.bude.zuulserver.repository.LoginRepository;
import com.bude.zuulserver.utils.JwtTokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String,String> registerUser){

        LoginEntity user = new LoginEntity();
        user.setUsername(registerUser.get("username"));
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        user.setRole("ROLE_USER");
        user.setState(1);
        userRepository.save(user);
        return JSON.toJSONString(true);
    }

    @PostMapping("/tokenBody")
    public String tokenBody(@RequestBody Map<String, String> mp) {
        String s = mp.get("token");
        return JwtTokenUtils.getUsername(s) + ":" + JwtTokenUtils.getUserRole(s);
    }


}
