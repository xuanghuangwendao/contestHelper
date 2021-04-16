package com.bude.userserver.service;


import com.bude.utils.model.UserForList;
import com.bude.utils.result.Result;

import java.util.List;

public interface ContestService {


    Result<List<UserForList>> getFollowUserList(int contestId, int myId);

    Result<Boolean> checkFollow(int contestId, int userId);

}
