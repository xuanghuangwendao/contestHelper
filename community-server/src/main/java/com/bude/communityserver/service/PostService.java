package com.bude.communityserver.service;


import com.bude.communityserver.entity.PostEntity;
import com.bude.communityserver.entity.PostItemEntity;
import com.bude.communityserver.result.Result;
import com.bude.communityserver.model.*;
import java.util.List;

public interface PostService {

    Result<PostForPage> getPostById(int postId, int myId);

    Result<List<PostForList>> getAllPost();

    Result<List<PostForList>> searchByName(String name);

    Result<Boolean> addPost(PostEntity postEntity, PostItemEntity postItemEntity);

}
