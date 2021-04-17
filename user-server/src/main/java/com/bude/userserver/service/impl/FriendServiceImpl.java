package com.bude.userserver.service.impl;

import com.bude.userserver.entity.UserEntity;
import com.bude.userserver.entity.UserUserEntity;
import com.bude.userserver.entity.UserUserEntityPK;
import com.bude.userserver.repository.UserRepository;
import com.bude.userserver.repository.UserUserRepository;
import com.bude.userserver.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bude.userserver.result.Result;
import com.bude.userserver.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUserRepository userUserRepository;

    @Override
    public Result<List<UserForList>> getFriendsById(int id) {
        List<UserUserEntity> uu1s = userUserRepository.findAllByUu1Equals(id);
        List<UserUserEntity> uu2s = userUserRepository.findAllByUu2Equals(id);
        List<UserEntity> users = new ArrayList<>();
        Result<List<UserForList>> result = new Result<>();
        for (UserUserEntity entity: uu1s) {
            Optional<UserEntity> user = userRepository.findById(entity.getUu2());
            if (user.isPresent()) {
                users.add(user.get());
            }
        }

//        //TODO
//        for (UserUserEntity entity: uu2s) {
//            Optional<UserEntity> user = userRepository.findById(entity.getUu1());
//            if (user.isPresent()) {
//                users.add(user.get());
//            }
//        }

        if (users.isEmpty()) {
            result.state = false;
            result.setData(null);
        }

        List<UserForList> lists = new ArrayList<>();
        for (UserEntity entity: users) {
            UserForList ul = new UserForList();
            ul.setId(entity.getId());
            ul.setDisplayName(entity.getDisplayname());
            ul.setTag(entity.getTag());
            ul.setFriend(true);
            ul.setSign(entity.getSign());
            lists.add(ul);
        }
        result.data = lists;
        return result;
    }

    @Override
    public Result<Boolean> checkFriend(int id1, int id2) {
        Result<Boolean> result = new Result<>(true);
        UserUserEntityPK pk1 = new UserUserEntityPK();
        pk1.setUu1(id1);
        pk1.setUu2(id2);
        boolean check1 = userUserRepository.existsById(pk1);
        UserUserEntityPK pk2 = new UserUserEntityPK();
        pk2.setUu1(id2);
        pk2.setUu2(id1);
        boolean check2 = userUserRepository.existsById(pk2);
        result.data = (check1 || check2);
        return result;

    }

    @Override
    public Result<Boolean> addFriend(int myId, int userId) {
        if (myId == userId) {
            return new Result<>(false);
        }
        UserUserEntity uu = new UserUserEntity();
        uu.setUu1(myId);
        uu.setUu2(userId);
        UserUserEntityPK uupk = new UserUserEntityPK();
        uupk.setUu1(myId);
        uupk.setUu2(userId);
        if (userUserRepository.findById(uupk).isPresent()) {
            return new Result<>(false);
        }
        userUserRepository.save(uu);
        return new Result<>(true);
    }
    @Override
    public Result<Boolean> cAddFriend(int myId, int userId) {
        if (myId == userId) {
            return new Result<>(false);
        }
        UserUserEntity uu = new UserUserEntity();
        uu.setUu1(myId);
        uu.setUu2(userId);
        UserUserEntityPK uupk = new UserUserEntityPK();
        uupk.setUu1(myId);
        uupk.setUu2(userId);
        userUserRepository.delete(uu);
        return new Result<>(true);
    }
}
