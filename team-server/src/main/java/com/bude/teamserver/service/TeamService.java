package com.bude.teamserver.service;

import com.bude.teamserver.entity.TeamEntity;
import com.bude.teamserver.result.Result;
import com.bude.teamserver.model.*;

import java.util.List;

public interface TeamService {

    Result<TeamForPage> getTeamById(int teamId, int myId);

    boolean checkMember(int teamId, int userId);

    Result<List<TeamForList>> getListByName(String name, int myId);

    Result<Boolean> joinTeam(int teamId, int myId);

    Result<Boolean> createTeam(TeamEntity entity);

}
