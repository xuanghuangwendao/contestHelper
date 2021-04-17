package com.bude.communityserver.service;

import com.bude.communityserver.entity.PostItemEntity;
import com.bude.communityserver.result.Result;
import com.bude.communityserver.model.*;

import java.util.List;

public interface ItemService {

    Result<List<PostItem>> getItemList(int postId);

    Result<Boolean> addItem(PostItemEntity entity);
}
