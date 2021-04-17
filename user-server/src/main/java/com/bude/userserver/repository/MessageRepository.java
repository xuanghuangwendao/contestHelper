package com.bude.userserver.repository;

import com.bude.userserver.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
    List<MessageEntity> findAllByAidEqualsOrSidEquals(int aid, int cid);
}
