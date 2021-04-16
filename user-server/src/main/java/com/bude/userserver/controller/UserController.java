package com.bude.userserver.controller;


import com.alibaba.fastjson.JSON;
import com.bude.userserver.entity.MessageEntity;
import com.bude.userserver.entity.UserEntity;
import com.bude.userserver.service.*;
import com.bude.utils.model.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bude.utils.result.Result;

import java.sql.Timestamp;
import java.util.Map;

@Log4j2
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @Autowired
    TeamMemberService teamMemberService;

    @Autowired
    ContestService contestService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/page")
    public String byId(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value = body.get("userId");
            String value2 = body.get("myId");
            int myId = Integer.parseInt(value2);
            int id = Integer.parseInt(value);
            String s = JSON.toJSONString(userService.getUserInfoById(id, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }


    @RequestMapping(value = "/searchByName")
    public String byName(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String displayName = body.get("displayName");
            String value2 = body.get("myId");
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(userService.getUserInfoListByName(displayName, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/friendList")
    public String friendList(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value = body.get("myId");
            int id = Integer.parseInt(value);
            String s = JSON.toJSONString(friendService.getFriendsById(id));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/teamMembers")
    public String teamMembers(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value = body.get("teamId");
            String value2 = body.get("myId");
            int id = Integer.parseInt(value);
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(teamMemberService.getMembers(id, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }
    @RequestMapping("/contestFollow")
    public String get(@RequestBody Map<String, String> body) {
        try {
            String value = body.get("contestId");
            String value2 = body.get("myId");
            int id = Integer.parseInt(value);
            int myId = Integer.parseInt(value2);
            log.info(body.toString());
            String s = JSON.toJSONString(contestService.getFollowUserList(id, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping(value = "/register")
    public String register(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value = body.get("userId");
            int id = Integer.parseInt(value);

            String displayName = body.get("displayName");
            String sign = body.get("sign");
            String school = body.get("school");
            String grade = body.get("grade");
            String major = body.get("major");
            String introduction = body.get("introduction");
            String tag = body.get("tag");
            if (id <= 0) {
                return Result.ParamError();
            }
            UserEntity user = new UserEntity();
            user.setId(id);
            user.setDisplayname(displayName);
            user.setSign(sign);
            user.setSchool(school);
            user.setGrade(grade);
            user.setMajor(major);
            user.setIntroduction(introduction);
            user.setTag(tag);

            Result<Boolean> result = userService.register(user);
            log.info(result.toString());
            return JSON.toJSONString("success");

        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/addFriend")
    public String addFriend(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String value1 = body.get("userId");
            String value2 = body.get("myId");
            int userId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(friendService.addFriend(myId,userId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }

    }
    @RequestMapping("/cAddFriend")
    public String cAddFriend(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String value1 = body.get("userId");
            String value2 = body.get("myId");
            int userId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(friendService.cAddFriend(myId,userId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }

    }
    @RequestMapping(value = "/getName")
    public String getName(@RequestParam Integer userId) {
        try {

            log.info(userId);
            int id = userId;
            String s = userService.getName(id).data;
            log.info(s);
            return s;
        } catch (Exception e) {
            return Result.ServerError();
        }
    }
    @RequestMapping(value = "/getSign")
    public String getSign(@RequestParam Integer userId) {
        try {

            log.info(userId);
            int id = userId;
            String s = userService.getSign(id).data;
            log.info(s);
            return s;
        } catch (Exception e) {
            return Result.ServerError();
        }
    }
    @RequestMapping(value = "/getTag")
    public String getTag(@RequestParam Integer userId) {
        try {
            log.info("getTag");
            log.info(userId);
            int id = userId;
            String s = userService.getTag(id).data;
            log.info(s);
            return s;
        } catch (Exception e) {
            return Result.ServerError();
        }
    }

    @RequestMapping(value = "/sendMessage")
    public String sendMessage(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String value1 = body.get("userId");
            String value2 = body.get("myId");
            int userId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String content = body.get("content");
            String value3 = body.get("type");
            int type = Integer.parseInt(value3);
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setSid(myId);
            messageEntity.setAid(userId);
            messageEntity.setContent(content);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            messageEntity.setTime(timestamp);
            messageEntity.setType(type);

            String s = JSON.toJSONString(messageService.sendMessage(messageEntity));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }


}
