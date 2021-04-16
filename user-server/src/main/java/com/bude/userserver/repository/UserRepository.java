package com.bude.userserver.repository;

import com.bude.userserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer>{
    List<UserEntity> findAllByDisplaynameLike(String name);

    UserEntity findByDisplaynameEquals(String name);
}
