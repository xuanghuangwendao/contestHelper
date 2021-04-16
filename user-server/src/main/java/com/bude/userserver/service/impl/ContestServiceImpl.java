package com.bude.userserver.service.impl;

import com.bude.userserver.entity.ContestUserEntity;
import com.bude.userserver.entity.ContestUserEntityPK;
import com.bude.userserver.entity.UserEntity;
import com.bude.userserver.repository.ContestUserRepository;
import com.bude.userserver.repository.UserRepository;
import com.bude.userserver.service.ContestService;
import com.bude.userserver.service.FriendService;
import com.bude.utils.model.UserForList;
import com.bude.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    ContestUserRepository contestUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendService friendService;

    @Override
    public Result<List<UserForList>> getFollowUserList(int contestId, int myId) {
        List<ContestUserEntity> cus = contestUserRepository.getAllByCidEquals(contestId);
        List<UserEntity> users = new ArrayList<>();
        Result<List<UserForList>> result = new Result<>();
        for (ContestUserEntity entity: cus) {
            Optional<UserEntity> user = userRepository.findById(entity.getUid());
            if (user.isPresent()) {
                users.add(user.get());
            } else {
                result.info.put(String.valueOf(entity.getUid()), "no found");
            }
        }
        if (result.data.isEmpty()) {
            result.setState(false);
            result.setData(null);
            result.info.put("e", "no found");
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
    public Result<Boolean> checkFollow(int contestId, int userId) {
        ContestUserEntityPK pk = new ContestUserEntityPK();
        pk.setCid(contestId);
        pk.setUid(userId);
        boolean exist = contestUserRepository.existsById(pk);
        return new Result<>(exist);
    }
}
