package com.bude.teamserver.service;

import com.bude.utils.model.TeamForList;
import com.bude.utils.result.Result;

import java.util.List;

public interface ContestService {
    Result<List<TeamForList>> getTeamListByContestId(int contestId, int myId);
}
