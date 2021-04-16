package com.bude.contestserver.repository;

import com.bude.contestserver.entity.NewsEntity;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<NewsEntity, Integer> {
}
