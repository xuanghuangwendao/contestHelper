package com.bude.userserver.service;


import com.bude.userserver.result.Result;
import com.bude.userserver.model.*;

import java.util.List;

public interface ContestService {


    Result<List<UserForList>> getFollowUserList(int contestId, int myId);

    Result<Boolean> checkFollow(int contestId, int userId);

}
