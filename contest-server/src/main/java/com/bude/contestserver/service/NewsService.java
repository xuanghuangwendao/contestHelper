package com.bude.contestserver.service;

import com.bude.contestserver.result.Result;
import com.bude.contestserver.model.*;

import java.util.List;

public interface NewsService {


    Result<NewsForList> getNewsById(int newsId);

    Result<NewsForList> getLastNews(int contestId);

    Result<List<NewsForList>> getNewsByContest(int contest);

}
