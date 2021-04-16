package com.bude.contestserver.service;

import com.bude.contestserver.entity.ContestEntity;
import com.bude.utils.model.ContestForList;
import com.bude.utils.model.ContestForPage;
import com.bude.utils.result.Result;

import java.util.List;

public interface ContestService {

    Result<ContestForPage> getContestById(int contestId, int myId);

    Result<List<ContestForList>> getContestListByName(String name, int myId);

    Result<List<ContestForList>> getAllContest(int myId);

    Result<List<ContestForList>> getRecommend(int myId);

    Result<Boolean> checkUserFollow(int contestId, int myId);

    Result<Boolean> checkTeamFollow(int contestId, int teamId);

    Result<List<ContestForList>> getUserFollowList(int myId);

    Result<Boolean> followContest(int contestId, int myId);

    Result<Boolean> cFollowContest(int contestId, int myId);

    Result<String> getName(int contestId);

    Result<Boolean> create(ContestEntity contestEntity);
}
