package com.bude.userserver.service;



import com.bude.utils.model.UserForList;

import com.bude.utils.result.Result;
import java.util.List;

public interface TeamMemberService {
    Result<List<UserForList>> getMembers(int teamId, int myId);


    Result<Boolean> check(int teamId, int userId);
}
