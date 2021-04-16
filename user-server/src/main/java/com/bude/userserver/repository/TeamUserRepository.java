package com.bude.userserver.repository;

import com.bude.userserver.entity.TeamUserEntity;
import com.bude.userserver.entity.TeamUserEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamUserRepository extends CrudRepository<TeamUserEntity, TeamUserEntityPK> {

    List<TeamUserEntity> findAllByTidEquals(int tid);
}
