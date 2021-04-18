package com.bude.userserver.repository;

import com.bude.userserver.entity.ContestTeamEntity;
import com.bude.userserver.entity.ContestTeamEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContestTeamRepository extends CrudRepository<ContestTeamEntity, ContestTeamEntityPK> {
    List<ContestTeamEntity> findAllByCidEquals(int cid);

}
