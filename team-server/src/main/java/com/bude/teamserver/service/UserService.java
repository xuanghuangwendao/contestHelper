package com.bude.teamserver.service;

import com.bude.teamserver.entity.TeamEntity;
import com.bude.utils.model.TeamForList;
import com.bude.utils.result.Result;

import java.util.List;

public interface UserService {
    Result<List<TeamForList>> getTeamListByUserId(int userId, int myId);

}
