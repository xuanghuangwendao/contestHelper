package com.bude.teamserver.service.impl;

import com.bude.teamserver.entity.TeamEntity;
import com.bude.teamserver.entity.TeamUserEntity;
import com.bude.teamserver.repository.TeamRepository;
import com.bude.teamserver.repository.TeamUserRepository;
import com.bude.teamserver.service.TeamService;
import com.bude.teamserver.service.UserFeign;
import com.bude.teamserver.service.UserService;
import com.bude.teamserver.result.Result;
import com.bude.teamserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamUserRepository teamUserRepository;


    @Autowired
    UserFeign userFeign;

    @Autowired
    TeamService teamService;

    @Override
    public Result<List<TeamForList>> getTeamListByUserId(int userId, int myId) {
        List<TeamUserEntity> list = teamUserRepository.findAllByUidEquals(userId);
        List<TeamEntity> teams = new ArrayList<>();
        Result<List<TeamForList>> result = new Result<>();
        for(TeamUserEntity entity: list) {
            Optional<TeamEntity> team = teamRepository.findById(entity.getTid());
            if (team.isPresent()) {
                teams.add(team.get());
            } else {
                result.getInfo().put(String.valueOf(entity.getTid()), "no found");
            }
        }
        if (teams.isEmpty()) {
            result.setState(false);
            result.setData(null);
        }

        List<TeamForList> lists = new ArrayList<>();
        for(TeamEntity entity: teams) {
            TeamForList tl = new TeamForList();
            tl.setId(entity.getId());
            tl.setName(entity.getName());
            tl.setTag(entity.getTag());
            tl.setLeaderName(userFeign.getName(entity.getLeader()));
            tl.setFollow(teamService.checkMember(entity.getId(), myId));
            lists.add(tl);
        }
        result.setData(lists);

        return result;
    }
}
