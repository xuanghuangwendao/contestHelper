package com.bude.teamserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.bude.teamserver.entity.ContestTeamEntity;
import com.bude.teamserver.entity.TeamEntity;
import com.bude.teamserver.entity.TeamUserEntity;
import com.bude.teamserver.entity.TeamUserEntityPK;
import com.bude.teamserver.repository.ContestTeamRepository;
import com.bude.teamserver.repository.TeamRepository;
import com.bude.teamserver.repository.TeamUserRepository;
import com.bude.teamserver.service.ContestFeign;
import com.bude.teamserver.service.TeamService;
import com.bude.teamserver.service.UserFeign;
import com.bude.teamserver.result.Result;
import com.bude.teamserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserFeign userFeign;

    @Autowired
    ContestFeign contestFeign;

    @Autowired
    TeamUserRepository teamUserRepository;

    @Autowired
    ContestTeamRepository contestTeamRepository;

    @Override
    public Result<TeamForPage> getTeamById(int teamId, int myId) {
        Result<TeamForPage> result = new Result<>();
        Optional<TeamEntity> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("e", "no found");

        } else {
            TeamForPage page = new TeamForPage();
            page.setId(team.get().getId());
            page.setName(team.get().getName());
            page.setSign(team.get().getSign());
            page.setLeaderId(team.get().getLeader());
            page.setContestId(team.get().getContest());
            page.setLeaderName(userFeign.getName(page.getLeaderId()));
            page.setContestName(contestFeign.getName(team.get().getContest()));
            page.setTag(team.get().getTag());
            page.setCheck(checkMember(teamId, myId));
            result.setData(page);
        }
        return result;
    }

    public boolean checkMember(int teamId, int userId) {
        TeamUserEntityPK pk = new TeamUserEntityPK();
        pk.setTid(teamId);
        pk.setUid(userId);
        if (teamUserRepository.findById(pk).isPresent()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Result<List<TeamForList>> getListByName(String name, int myId) {
        Result<List<TeamForList>> result = new Result<>();
        List<TeamEntity> teams = teamRepository.findAllByNameLike("%" + name + "%");
        if (teams == null) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("data", "no found");
        } else {
            List<TeamForList> lists = new ArrayList<>();
            for(TeamEntity entity: teams) {
                TeamForList tl = new TeamForList();
                tl.setId(entity.getId());
                tl.setName(entity.getName());
                tl.setTag(entity.getTag());
                tl.setLeaderName(userFeign.getName(entity.getLeader()));
                tl.setFollow(checkMember(entity.getId(), myId));
                lists.add(tl);
            }
            result.setData(lists);
        }
        return result;
    }

    @Override
    public Result<Boolean> joinTeam(int teamId, int myId) {
        TeamUserEntity entity = new TeamUserEntity();
        entity.setUid(myId);
        entity.setTid(teamId);
        TeamUserEntityPK pk = new TeamUserEntityPK();
        pk.setTid(teamId);
        pk.setUid(myId);
        if (teamUserRepository.findById(pk).isPresent()) {
            return new Result<>(false);
        } else {
            teamUserRepository.save(entity);
            return new Result<>(true);
        }
    }

    @Override
    public Result<Boolean> createTeam(TeamEntity entity) {
        TeamEntity team = teamRepository.save(entity);


        TeamUserEntity tu = new TeamUserEntity();

        tu.setTid(team.getId());
        tu.setUid(team.getLeader());
        teamUserRepository.save(tu);

        ContestTeamEntity ct = new ContestTeamEntity();
        ct.setTid(team.getId());
        ct.setCid(team.getContest());
        contestTeamRepository.save(ct);


        return new Result<>(true);


    }

}
