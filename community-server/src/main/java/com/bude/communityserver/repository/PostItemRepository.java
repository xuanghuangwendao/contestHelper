package com.bude.communityserver.repository;

import com.bude.communityserver.entity.PostItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostItemRepository extends CrudRepository<PostItemEntity, Integer> {
    List<PostItemEntity> findAllByPidEquals(int pid);

}
