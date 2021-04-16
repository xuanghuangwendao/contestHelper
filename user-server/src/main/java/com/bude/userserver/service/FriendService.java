package com.bude.userserver.service;



import com.bude.utils.model.UserForList;

import com.bude.utils.result.Result;
import java.util.List;

public interface FriendService {

    Result<List<UserForList>> getFriendsById(int id);

    Result<Boolean> checkFriend(int id1, int id2);

    Result<Boolean> addFriend(int myId, int userId);

    Result<Boolean> cAddFriend(int myId, int userId);

}
