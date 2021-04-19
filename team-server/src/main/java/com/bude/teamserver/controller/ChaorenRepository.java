package com.bude.teamserver.controller;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChaorenRepository extends CrudRepository<ChaorenEntity, Integer> {

    List<ChaorenEntity> findAllByTimeEqualsAndQqEquals(String time, String qq);

}
