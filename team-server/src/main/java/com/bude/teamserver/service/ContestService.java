package com.bude.teamserver.service;

import com.bude.teamserver.result.Result;
import com.bude.teamserver.model.*;

import java.util.List;

public interface ContestService {
    Result<List<TeamForList>> getTeamListByContestId(int contestId, int myId);
}
