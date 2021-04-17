package com.bude.teamserver.service;

import com.bude.teamserver.entity.TeamEntity;
import com.bude.teamserver.result.Result;
import com.bude.teamserver.model.*;
import com.bude.teamserver.model.TeamForList;

import java.util.List;

public interface UserService {
    Result<List<TeamForList>> getTeamListByUserId(int userId, int myId);

}
