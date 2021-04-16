package com.bude.collectengine.utils;

import com.bude.collectengine.entity.ContestNewsEntity;
import com.bude.collectengine.entity.NewsEntity;
import com.bude.collectengine.repository.ContestNewsRepository;
import com.bude.collectengine.repository.NewsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Log4j2
@Repository
public class NewsUtil {


    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ContestNewsRepository contestNewsRepository;

    public void save(String title, String newsUrl, Timestamp timestamp, String cid) {
        NewsEntity news = new NewsEntity();
        news.setTitle(title);
        news.setUrl(newsUrl);
        news.setTime(timestamp);

        List<NewsEntity> list = newsRepository.findAllByTitleEqualsAndTimeEquals(title, news.getTime());
        if (list.size() == 0) {
            news = newsRepository.save(news);
            ContestNewsEntity cn = new ContestNewsEntity();
            cn.setCid(Integer.parseInt(cid));
            cn.setNid(news.getId());
            contestNewsRepository.save(cn);
            log.info("contest:" + cid + " insert " + news.getTitle() + " for id " + news.getId());
        } else {
            log.info("contest:" + cid + " exist " + news.getTitle() + " for id " + list.get(0).getId());
        }
    }
}
