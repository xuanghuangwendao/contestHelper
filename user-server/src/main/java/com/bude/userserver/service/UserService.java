package com.bude.userserver.service;


import com.bude.userserver.entity.UserEntity;
import com.bude.userserver.result.Result;
import com.bude.userserver.model.*;
import java.util.List;

public interface UserService {

    Result<UserForPage> getUserInfoById(Integer id, int me);

    Result<String> getName(int userId);

    Result<String> getSign(int userId);

    Result<String> getTag(int userId);

    Result<List<UserForList>> getUserInfoListByName(String displayName, int me);

    Result<List<UserForList>> getUserInfoListByIdList(List<Integer> ids, int me);

    Result<Boolean> register(UserEntity user);

}
