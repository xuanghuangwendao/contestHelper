package com.bude.userserver.service;


import com.bude.userserver.entity.UserEntity;
import com.bude.utils.model.UserForList;
import com.bude.utils.model.UserForPage;

import com.bude.utils.result.Result;
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
