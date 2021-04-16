package com.bude.zuulserver.repository;


import com.bude.zuulserver.entity.LoginEntity;
import org.springframework.data.repository.CrudRepository;


public interface LoginRepository extends CrudRepository<LoginEntity, Integer> {
    LoginEntity findByUsername(String username);
}