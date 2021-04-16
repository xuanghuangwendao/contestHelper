package com.bude.contestserver.repository;

import com.bude.contestserver.entity.ContestTeamEntity;
import com.bude.contestserver.entity.ContestTeamEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContestTeamRepository extends CrudRepository<ContestTeamEntity, ContestTeamEntityPK> {

    List<ContestTeamEntity> findAllByTidEquals(int tid);
}
