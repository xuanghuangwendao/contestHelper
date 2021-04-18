package com.bude.userserver.service;


import com.bude.userserver.entity.TeamEntity;
import com.bude.userserver.model.TeamForList;
import com.bude.userserver.model.TeamForPage;
import com.bude.userserver.result.Result;

import java.util.List;

public interface TeamService {

    Result<TeamForPage> getTeamById(int teamId, int myId);

    boolean checkMember(int teamId, int userId);

    Result<List<TeamForList>> getListByName(String name, int myId);

    Result<Boolean> joinTeam(int teamId, int myId);

    Result<Boolean> createTeam(TeamEntity entity);

    Result<List<TeamForList>> getTeamListByContestId(int contestId, int myId);

    Result<List<TeamForList>> getTeamListByUserId(int userId, int myId);

}
