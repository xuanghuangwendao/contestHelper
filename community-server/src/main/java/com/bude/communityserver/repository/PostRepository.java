package com.bude.communityserver.repository;

import com.bude.communityserver.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {

    List<PostEntity> findAllByTitleLike(String title);
}
