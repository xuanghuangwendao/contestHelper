package com.bude.teamserver.repository;

import com.bude.teamserver.entity.ContestTeamEntity;
import com.bude.teamserver.entity.ContestTeamEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContestTeamRepository extends CrudRepository<ContestTeamEntity, ContestTeamEntityPK> {
    List<ContestTeamEntity> findAllByCidEquals(int cid);

}
