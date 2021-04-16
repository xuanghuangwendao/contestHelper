package com.bude.collectengine.repository;

import com.bude.collectengine.entity.ContestNewsEntity;
import com.bude.collectengine.entity.ContestNewsEntityPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContestNewsRepository extends CrudRepository<ContestNewsEntity, ContestNewsEntityPK> {

}
