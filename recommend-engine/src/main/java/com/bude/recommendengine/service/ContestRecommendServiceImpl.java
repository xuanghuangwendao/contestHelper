package com.bude.recommendengine.service;

import com.bude.recommendengine.cache.ContestCache;
import com.bude.recommendengine.entity.ContestEntity;
import com.bude.recommendengine.entity.UserEntity;
import com.bude.utils.model.ContestForList;
import com.bude.utils.model.UserForList;
import com.bude.utils.model.UserForPage;
import com.bude.utils.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ContestRecommendServiceImpl implements ContestRecommendService {

    @Autowired
    ContestService contestService;

    @Autowired
    UserService userService;

    @Override
    public Result<List<ContestForList>> getRecommendList(int userId) {
        Result<List<ContestForList>> result = new Result<>();
        UserForPage user = userService.getUserInfoById(userId, userId).getData();
        if (user == null) {
            result.state = false;
            return result;
        }
        ContestCache cache = ContestCache.getInstance();
        List<ContestForList> list = new ArrayList<>();
        List<String> tags = Arrays.asList(StringUtils.split(user.getTag(), ";"));

        for (String tag: tags) {
            list.addAll(cache.query(tag));
        }
        list = list.stream().distinct().collect(Collectors.toList());
        for (ContestForList item: list) {

        }

        result.data = list;
        return result;

    }
}
