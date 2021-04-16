package com.bude.collectengine.repository;

import com.bude.collectengine.entity.NewsEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface NewsRepository extends CrudRepository<NewsEntity, Integer> {

    List<NewsEntity> findAllByTitleEqualsAndTimeEquals(String title, Timestamp timestamp);

}
