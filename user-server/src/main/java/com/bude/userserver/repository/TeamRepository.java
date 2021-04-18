package com.bude.userserver.repository;

import com.bude.userserver.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
    List<TeamEntity> findAllByNameLike(String name);
}
