package com.bude.teamserver.repository;

import com.bude.teamserver.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
    List<TeamEntity> findAllByNameLike(String name);
}
