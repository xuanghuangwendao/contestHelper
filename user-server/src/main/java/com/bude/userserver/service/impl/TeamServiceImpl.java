package com.bude.userserver.service.impl;

import com.bude.userserver.entity.ContestTeamEntity;
import com.bude.userserver.entity.TeamEntity;
import com.bude.userserver.entity.TeamUserEntity;
import com.bude.userserver.entity.TeamUserEntityPK;
import com.bude.userserver.model.TeamForList;
import com.bude.userserver.model.TeamForPage;
import com.bude.userserver.repository.ContestTeamRepository;
import com.bude.userserver.repository.TeamRepository;
import com.bude.userserver.repository.TeamUserRepository;
import com.bude.userserver.result.Result;
import com.bude.userserver.service.ContestFeign;
import com.bude.userserver.service.TeamService;
import com.bude.userserver.service.UserService;
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
    UserService userService;

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
            page.setLeaderName(userService.getName(page.getLeaderId()).data);
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
                tl.setLeaderName(userService.getName(entity.getLeader()).data);
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

    @Override
    public Result<List<TeamForList>> getTeamListByContestId(int contestId, int myId) {
        List<ContestTeamEntity> list = contestTeamRepository.findAllByCidEquals(contestId);
        List<TeamEntity> teams = new ArrayList<>();
        Result<List<TeamForList>> result = new Result<>();
        for(ContestTeamEntity entity: list) {
            Optional<TeamEntity> team = teamRepository.findById(entity.getTid());
            if (team.isPresent()) {
                teams.add(team.get());
            } else {
                result.getInfo().put(String.valueOf(entity.getTid()), "no found");
            }
        }

        List<TeamForList> lists = new ArrayList<>();
        for(TeamEntity entity: teams) {
            TeamForList tl = new TeamForList();
            tl.setId(entity.getId());
            tl.setName(entity.getName());
            tl.setTag(entity.getTag());
            tl.setLeaderName(userService.getName(entity.getLeader()).data);
            tl.setFollow(checkMember(entity.getId(), myId));
            lists.add(tl);
        }
        result.setData(lists);


        return result;
    }

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
            tl.setLeaderName(userService.getName(entity.getLeader()).data);
            tl.setFollow(checkMember(entity.getId(), myId));
            lists.add(tl);
        }
        result.setData(lists);

        return result;
    }
}
