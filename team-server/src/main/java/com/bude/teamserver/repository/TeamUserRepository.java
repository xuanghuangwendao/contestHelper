package com.bude.teamserver.repository;

import com.bude.teamserver.entity.TeamUserEntity;
import com.bude.teamserver.entity.TeamUserEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamUserRepository extends CrudRepository<TeamUserEntity, TeamUserEntityPK> {
    List<TeamUserEntity> findAllByUidEquals(int uid);
}

