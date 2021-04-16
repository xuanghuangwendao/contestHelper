package com.bude.contestserver.repository;

import com.bude.contestserver.entity.ContestUserEntity;
import com.bude.contestserver.entity.ContestUserEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContestUserRepository extends CrudRepository<ContestUserEntity, ContestUserEntityPK> {

    List<ContestUserEntity> findAllByUidEquals(int uid);
}
