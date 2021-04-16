package com.bude.contestserver.repository;

import com.bude.contestserver.entity.ContestNewsEntity;
import com.bude.contestserver.entity.ContestNewsEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContestNewsRepository extends CrudRepository<ContestNewsEntity, ContestNewsEntityPK> {

    List<ContestNewsEntity> findAllByCidEquals(int cid);


}
