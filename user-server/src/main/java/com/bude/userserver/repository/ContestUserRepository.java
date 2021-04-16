package com.bude.userserver.repository;


import com.bude.userserver.entity.ContestUserEntity;
import com.bude.userserver.entity.ContestUserEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContestUserRepository extends CrudRepository<ContestUserEntity, ContestUserEntityPK> {
    List<ContestUserEntity> getAllByCidEquals(int cid);

}
