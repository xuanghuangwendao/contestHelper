package com.bude.userserver.service;



import com.bude.userserver.result.Result;
import com.bude.userserver.model.*;
import java.util.List;

public interface FriendService {

    Result<List<UserForList>> getFriendsById(int id);

    Result<Boolean> checkFriend(int id1, int id2);

    Result<Boolean> addFriend(int myId, int userId);

    Result<Boolean> cAddFriend(int myId, int userId);

}
