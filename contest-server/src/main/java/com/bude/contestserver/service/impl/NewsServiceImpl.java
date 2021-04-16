package com.bude.contestserver.service.impl;

import com.bude.contestserver.entity.ContestNewsEntity;
import com.bude.contestserver.entity.NewsEntity;
import com.bude.contestserver.repository.ContestNewsRepository;
import com.bude.contestserver.repository.NewsRepository;
import com.bude.contestserver.service.NewsService;
import com.bude.utils.model.NewsForList;
import com.bude.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ContestNewsRepository contestNewsRepository;


    @Override
    public Result<NewsForList> getNewsById(int newsId) {

        Result<NewsForList> result = new Result<>();
        Optional<NewsEntity> news = newsRepository.findById(newsId);
        if (news.isEmpty()) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("data", "no found");

        } else {
            NewsForList n = new NewsForList();
            n.setId(news.get().getId());
            n.setTime(new SimpleDateFormat("yyyy-MM-dd").format(news.get().getTime()));
            n.setTitle(news.get().getTitle());
            n.setUrl(news.get().getUrl());
            result.setData(n);
        }
        return result;
    }

    @Override
    public Result<NewsForList> getLastNews(int contestId) {
        Result<NewsForList> result = new Result<>();
        List<ContestNewsEntity> list = contestNewsRepository.findAllByCidEquals(contestId);
        if (list.size() == 0) {
            result.setState(false);
            return result;
        } else  {
            ContestNewsEntity entity = list.get(list.size()-1);
            return getNewsById(entity.getNid());
        }
    }

    @Override
    public Result<List<NewsForList>> getNewsByContest(int contest) {
        List<ContestNewsEntity> newsList = contestNewsRepository.findAllByCidEquals(contest);
        if (newsList.isEmpty()) {
            return new Result<>(false, null, Map.of("data", "no found"));
        }
        List<NewsForList> lists = new ArrayList<>();
        for (ContestNewsEntity entity: newsList) {
            lists.add(getNewsById(entity.getNid()).data);
        }
        return new Result<>(lists);

    }
}
