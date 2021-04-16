package com.bude.userserver.repository;

import com.bude.userserver.entity.UserUserEntity;
import com.bude.userserver.entity.UserUserEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserUserRepository extends CrudRepository<UserUserEntity, UserUserEntityPK> {

    List<UserUserEntity> findAllByUu1Equals(int id);

    List<UserUserEntity> findAllByUu2Equals(int id);
}
