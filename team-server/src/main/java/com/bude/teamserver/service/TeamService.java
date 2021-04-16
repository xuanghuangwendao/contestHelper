package com.bude.teamserver.service;

import com.bude.teamserver.entity.TeamEntity;
import com.bude.utils.model.TeamForList;
import com.bude.utils.model.TeamForPage;
import com.bude.utils.result.Result;

import java.util.List;

public interface TeamService {

    Result<TeamForPage> getTeamById(int teamId, int myId);

    boolean checkMember(int teamId, int userId);

    Result<List<TeamForList>> getListByName(String name, int myId);

    Result<Boolean> joinTeam(int teamId, int myId);

    Result<Boolean> createTeam(TeamEntity entity);

}
