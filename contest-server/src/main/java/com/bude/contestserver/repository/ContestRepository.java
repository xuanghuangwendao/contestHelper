package com.bude.contestserver.repository;

import com.bude.contestserver.entity.ContestEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContestRepository extends CrudRepository<ContestEntity, Integer> {
    List<ContestEntity> findAllByNameLike(String name);
}
