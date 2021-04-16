package com.bude.contestserver.service;

import com.bude.utils.model.NewsForList;
import com.bude.utils.result.Result;

import java.util.List;

public interface NewsService {


    Result<NewsForList> getNewsById(int newsId);

    Result<NewsForList> getLastNews(int contestId);

    Result<List<NewsForList>> getNewsByContest(int contest);

}
