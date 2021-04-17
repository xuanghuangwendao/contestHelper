package com.bude.userserver.service.impl;

import com.bude.userserver.entity.TeamUserEntity;
import com.bude.userserver.entity.TeamUserEntityPK;
import com.bude.userserver.entity.UserEntity;
import com.bude.userserver.repository.TeamUserRepository;
import com.bude.userserver.repository.UserRepository;
import com.bude.userserver.service.FriendService;
import com.bude.userserver.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bude.userserver.result.Result;
import com.bude.userserver.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    TeamUserRepository teamUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendService friendService;

    @Override
    public Result<List<UserForList>> getMembers(int teamId, int myId) {
        List<TeamUserEntity> list = teamUserRepository.findAllByTidEquals(teamId);
        List<UserEntity> users = new ArrayList<>();
        Result<List<UserForList>> result = new Result<>();
        for(TeamUserEntity entity: list) {
            Optional<UserEntity> user = userRepository.findById(entity.getUid());
            if (user.isPresent()) {
                users.add(user.get());
            }
        }
        if (users.isEmpty()) {
            result.setState(false);
            result.setData(null);
        }
        List<UserForList> lists = new ArrayList<>();
        for (UserEntity entity: users) {
            UserForList ul = new UserForList();
            ul.setId(entity.getId());
            ul.setDisplayName(entity.getDisplayname());
            ul.setTag(entity.getTag());
            ul.setSign(entity.getSign());
            ul.setFriend(friendService.checkFriend(entity.getId(), myId).data);
            lists.add(ul);
        }
        result.data = lists;
        return result;

    }



    @Override
    public Result<Boolean> check(int teamId, int userId) {
        Result<Boolean> result = new Result<>();
        TeamUserEntityPK pk = new TeamUserEntityPK();
        pk.setTid(teamId);
        pk.setUid(userId);
        result.setData(teamUserRepository.existsById(pk));
        return result;
    }
}
