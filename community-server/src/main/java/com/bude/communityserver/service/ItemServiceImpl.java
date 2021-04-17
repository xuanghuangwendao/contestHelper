package com.bude.communityserver.service;

import com.bude.communityserver.entity.PostItemEntity;
import com.bude.communityserver.repository.PostItemRepository;
import com.bude.communityserver.result.Result;
import com.bude.communityserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    PostItemRepository postItemRepository;

    @Autowired
    UserFeign userFeign;

    @Override
    public Result<List<PostItem>> getItemList(int postId) {
        List<PostItemEntity> list = postItemRepository.findAllByPidEquals(postId);
        List<PostItem> items = new ArrayList<>();
        int num = 1;
        for (PostItemEntity entity: list) {
            PostItem item = new PostItem();
            item.setId(entity.getId());
            item.setUserName(userFeign.getName(entity.getUid()));
            item.setUserId(entity.getUid());
            item.setPostId(entity.getPid());
            item.setContent(entity.getContent());
            item.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getTime()));
            item.setOrder(num++);
            items.add(item);
        }
        return new Result<>(items);
    }

    @Override
    public Result<Boolean> addItem(PostItemEntity entity) {

        postItemRepository.save(entity);
        return new Result<>(true);

    }
}
