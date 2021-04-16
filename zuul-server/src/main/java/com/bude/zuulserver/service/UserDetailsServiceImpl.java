package com.bude.zuulserver.service;

import com.bude.utils.model.Login;
import com.bude.zuulserver.entity.JwtUser;
import com.bude.zuulserver.entity.LoginEntity;
import com.bude.zuulserver.repository.LoginRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LoginEntity user = loginRepository.findByUsername(s);
        Login login = new Login();
        login.setId(user.getId());
        login.setUsername(user.getUsername());
        login.setPassword(user.getPassword());
        try {
            login.setDisplayName(userFeign.getName(login.getId()));
            login.setSign(userFeign.getSign(login.getId()));

        } catch (Exception e) {
            log.info(e.toString());
            login.setDisplayName("NULL");
            login.setSign("NULL");
            login.setSign("NULL");
        }
        login.setState(user.getState());
        login.setRole(user.getRole());

        return new JwtUser(login);
    }

}
