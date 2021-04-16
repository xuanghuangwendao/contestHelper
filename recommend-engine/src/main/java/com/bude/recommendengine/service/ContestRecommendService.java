package com.bude.recommendengine.service;

import com.bude.utils.model.ContestForList;
import com.bude.utils.result.Result;

import java.util.List;

public interface ContestRecommendService {

    Result<List<ContestForList>> getRecommendList(int userId);

}
