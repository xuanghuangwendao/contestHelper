package com.bude.communityserver.controller;


import com.alibaba.fastjson.JSON;
import com.bude.communityserver.entity.PostEntity;
import com.bude.communityserver.entity.PostItemEntity;
import com.bude.communityserver.service.ItemService;
import com.bude.communityserver.service.PostService;
import com.bude.utils.model.PostItem;
import com.bude.utils.result.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    ItemService itemService;


    @RequestMapping("/page")
    public String byId(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String value1 = body.get("postId");
            String value2 = body.get("myId");
            int postId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(postService.getPostById(postId, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/list")
    public String list(@RequestBody Map<String, String> body) {
        try {

            log.info(body.toString());
            String s = JSON.toJSONString(postService.getAllPost());
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }


    @RequestMapping("/search")
    public String search(@RequestBody Map<String, String> body) {
        try {

            log.info(body.toString());
            String condition = body.get("condition");
            String s = JSON.toJSONString(postService.getAllPost());
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }
    @RequestMapping("/addPost")
    public String addPost(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            int uid = Integer.parseInt(body.get("myId"));
            int cid = Integer.parseInt(body.get("cid"));
            String title = body.get("title");
            String content = body.get("content");
            PostEntity entity = new PostEntity();
            entity.setUid(uid);
            entity.setCid(cid);
            entity.setTitle(title);
            long now = System.currentTimeMillis();
            entity.setTime(new Timestamp(now));
            PostItemEntity postItemEntity = new PostItemEntity();
            postItemEntity.setUid(uid);
            postItemEntity.setContent(content);
            postItemEntity.setTime(new Timestamp(now));
            String s = JSON.toJSONString(postService.addPost(entity, postItemEntity));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/addItem")
    public String addItem(@RequestBody Map<String, String> body) {
        try {

            log.info(body.toString());
            int pid = Integer.parseInt(body.get("pid"));
            int uid = Integer.parseInt(body.get("myId"));
            String content = body.get("content");
            long now = System.currentTimeMillis();
            PostItemEntity item = new PostItemEntity();
            item.setPid(pid);
            item.setContent(content);
            item.setUid(uid);
            item.setTime(new Timestamp(now));
            String s = JSON.toJSONString(itemService.addItem(item));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

}
