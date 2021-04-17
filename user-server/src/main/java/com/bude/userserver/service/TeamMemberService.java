package com.bude.userserver.service;



import com.bude.userserver.result.Result;
import com.bude.userserver.model.*;
import java.util.List;

public interface TeamMemberService {
    Result<List<UserForList>> getMembers(int teamId, int myId);


    Result<Boolean> check(int teamId, int userId);
}
