package com.bude.userserver.service.impl;

import com.bude.userserver.entity.UserEntity;
import com.bude.userserver.repository.UserRepository;
import com.bude.userserver.service.FriendService;
import com.bude.userserver.service.UserService;
import com.bude.utils.model.UserForList;
import com.bude.utils.model.UserForPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bude.utils.result.Result;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendService friendService;


    @Override
    public Result<UserForPage> getUserInfoById(Integer id, int me) {

        Result<UserForPage> result = new Result<>();
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("data", "no found");

        } else {
            UserForPage page = new UserForPage();
            page.setId(user.get().getId());
            page.setDisplayName(user.get().getDisplayname());
            page.setSign(user.get().getSign());
            page.setSchool(user.get().getSchool());
            page.setMajor(user.get().getMajor());
            page.setGrade(user.get().getGrade());
            page.setGrade(user.get().getGrade());
            page.setTag(user.get().getTag());
            page.setFriend(friendService.checkFriend(id, me).data);
            page.setIntroduction(user.get().getIntroduction());
            result.setData(page);
        }
        return result;

    }

    @Override
    public Result<String> getName(int userId) {

        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new Result<>(true, user.get().getDisplayname());
        } else {
            return new Result<>(false, "暂无此人", Map.of("data", "no found"));
        }
    }

    @Override
    public Result<String> getSign(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new Result<>(true, user.get().getSign());
        } else {
            return new Result<>(false, "无", Map.of("data", "no found"));
        }
    }

    @Override
    public Result<String> getTag(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new Result<>(true, user.get().getTag());
        } else {
            return new Result<>(false, "综合", Map.of("data", "no found"));
        }
    }


    @Override
    public Result<List<UserForList>> getUserInfoListByName(String displayName, int me) {
        Result<List<UserForList>> result = new Result<>();
        List<UserEntity> users = userRepository.findAllByDisplaynameLike(displayName);
        if (users == null) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("data", "no found");
        } else {
            List<UserForList> lists = new ArrayList<>();
            for(UserEntity entity: users) {
                UserForList ul = new UserForList();
                ul.setId(entity.getId());
                ul.setDisplayName(entity.getDisplayname());
                ul.setTag(entity.getTag());
                ul.setSign(entity.getSign());
                ul.setFriend(friendService.checkFriend(entity.getId(), me).data);
                lists.add(ul);
            }
            result.setData(lists);
        }
        return result;
    }

    @Override
    public Result<List<UserForList>> getUserInfoListByIdList(List<Integer> ids, int me) {
        Result<List<UserForList>> result = new Result<>();

        List<UserEntity> users = (List<UserEntity>) userRepository.findAllById(ids);
        if (users.isEmpty()) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("data", "no found");
        } else {
            List<UserForList> lists = new ArrayList<>();
            for(UserEntity entity: users) {
                UserForList ul = new UserForList();
                ul.setId(entity.getId());
                ul.setDisplayName(entity.getDisplayname());
                ul.setTag(entity.getTag());
                ul.setSign(entity.getSign());
                ul.setFriend(friendService.checkFriend(entity.getId(), me).data);
                lists.add(ul);
            }
            result.setData(lists);
        }
        return result;

    }

    @Override
    public Result<Boolean> register(UserEntity user) {

        Optional<UserEntity> find = userRepository.findById(user.getId());
        if (find.isPresent()) {
            return new Result<>(false);
        }
        userRepository.save(user);
        return new Result<>(true);
    }
}
