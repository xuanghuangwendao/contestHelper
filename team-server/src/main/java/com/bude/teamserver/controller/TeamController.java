package com.bude.teamserver.controller;

import com.alibaba.fastjson.JSON;
import com.bude.teamserver.entity.TeamEntity;
import com.bude.teamserver.service.ContestService;
import com.bude.teamserver.service.TeamService;
import com.bude.teamserver.service.UserService;
import com.bude.teamserver.result.Result;
import com.bude.teamserver.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@Log4j2
@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @Autowired
    ContestService contestService;

    @RequestMapping("/page")
    public String byId(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String value1 = body.get("teamId");
            String value2 = body.get("myId");
            int teamId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String s =JSON.toJSONString(teamService.getTeamById(teamId, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/searchByName")
    public String searchByName(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String name = body.get("name");
            String value2 = body.get("myId");
            int myId = Integer.parseInt(value2);
            String s =  JSON.toJSONString(teamService.getListByName(name, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }
    @RequestMapping("/userJoinList")
    public String userJoinList(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String value1 = body.get("userId");
            String value2 = body.get("myId");
            int userId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(userService.getTeamListByUserId(userId, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);

            return Result.ServerError();
        }
    }
    @RequestMapping("/contestFollowList")
    public String contestFollowList(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String value1 = body.get("contestId");
            String value2 = body.get("myId");
            int teamId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(contestService.getTeamListByContestId(teamId, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/joinTeam")
    public String joinTeam(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String value1 = body.get("teamId");
            String value2 = body.get("myId");
            int teamId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(teamService.joinTeam(teamId, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/create")
    public String create(@RequestBody Map<String, String> body) {
        try {
            log.info(body.toString());
            String name = body.get("name");
            String sign = body.get("sign");
            String value2 = body.get("myId");
            int leader = Integer.parseInt(value2);
            String value3 = body.get("contest");
            int contest = Integer.parseInt(value3);
            String tag = body.get("tag");

            TeamEntity entity = new TeamEntity();
            entity.setName(name);
            entity.setSign(sign);
            entity.setLeader(leader);
            entity.setContest(contest);
            entity.setTag(tag);
            String s = JSON.toJSONString(teamService.createTeam(entity));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

}
