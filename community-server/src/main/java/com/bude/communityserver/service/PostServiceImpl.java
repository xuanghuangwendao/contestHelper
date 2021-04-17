package com.bude.communityserver.service;

import com.bude.communityserver.entity.PostEntity;
import com.bude.communityserver.entity.PostItemEntity;
import com.bude.communityserver.repository.PostItemRepository;
import com.bude.communityserver.repository.PostRepository;
import com.bude.communityserver.result.Result;
import com.bude.communityserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostItemRepository itemRepository;

    @Autowired
    UserFeign userFeign;

    @Autowired
    ContestFeign contestFeign;

    @Autowired
    ItemService itemService;

    @Autowired
    PostItemRepository postItemRepository;

    @Override
    public Result<PostForPage> getPostById(int postId, int myId) {

        Result<PostForPage> result = new Result<>();

        Optional<PostEntity> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("e", "no found");

        } else {
            PostForPage page = new PostForPage();
            page.setId(post.get().getId());
            page.setTitle(post.get().getTitle());
            page.setUserName(userFeign.getName(post.get().getUid()));
            page.setUserId(post.get().getUid());
            page.setContestName(contestFeign.getName(post.get().getCid()));
            page.setContestId(post.get().getCid());
            page.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.get().getTime()));
            page.setItems(itemService.getItemList(postId).data);
            result.setData(page);
        }
        return result;
    }

    @Override
    public Result<List<PostForList>> getAllPost() {
        List<PostEntity> entityList = (List<PostEntity>) postRepository.findAll();

        List<PostForList> list = new ArrayList<>();
        for (PostEntity entity: entityList) {
            PostForList item = new PostForList();
            item.setId(entity.getId());
            item.setTitle(entity.getTitle());
            item.setUserName(userFeign.getName(entity.getUid()));
            item.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getTime()));
            item.setContestName(contestFeign.getName(entity.getCid()));
            list.add(item);
        }
        return new Result<>(list);
    }

    @Override
    public Result<List<PostForList>> searchByName(String name) {
        List<PostEntity> entityList = (List<PostEntity>) postRepository.findAllByTitleLike("%" + name + "%");

        List<PostForList> list = new ArrayList<>();
        for (PostEntity entity: entityList) {
            PostForList item = new PostForList();
            item.setId(entity.getId());
            item.setTitle(entity.getTitle());
            item.setUserName(userFeign.getName(entity.getUid()));
            item.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getTime()));
            item.setContestName(contestFeign.getName(entity.getCid()));
            list.add(item);
        }
        return new Result<>(list);
    }

    @Override
    public Result<Boolean> addPost(PostEntity postEntity, PostItemEntity postItemEntity) {

        PostEntity entity1 = postRepository.save(postEntity);
        postItemEntity.setPid(entity1.getId());
        postItemRepository.save(postItemEntity);
        Result<Boolean> result = new Result<>(true);
        return result;
    }


}
