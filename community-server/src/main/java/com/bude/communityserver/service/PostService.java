package com.bude.communityserver.service;


import com.bude.communityserver.entity.PostEntity;
import com.bude.communityserver.entity.PostItemEntity;
import com.bude.utils.model.PostForList;
import com.bude.utils.model.PostForPage;
import com.bude.utils.model.PostItem;
import com.bude.utils.result.Result;

import java.util.List;

public interface PostService {

    Result<PostForPage> getPostById(int postId, int myId);

    Result<List<PostForList>> getAllPost();

    Result<List<PostForList>> searchByName(String name);

    Result<Boolean> addPost(PostEntity postEntity, PostItemEntity postItemEntity);

}
