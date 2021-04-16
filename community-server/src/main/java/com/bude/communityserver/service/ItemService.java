package com.bude.communityserver.service;

import com.bude.communityserver.entity.PostItemEntity;
import com.bude.utils.model.PostItem;
import com.bude.utils.result.Result;

import java.util.List;

public interface ItemService {

    Result<List<PostItem>> getItemList(int postId);

    Result<Boolean> addItem(PostItemEntity entity);
}
