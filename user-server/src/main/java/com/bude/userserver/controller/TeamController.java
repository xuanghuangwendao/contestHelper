package com.bude.userserver.controller;

import com.alibaba.fastjson.JSON;
import com.bude.userserver.entity.TeamEntity;
import com.bude.userserver.result.Result;
import com.bude.userserver.service.ContestService;
import com.bude.userserver.service.TeamService;
import com.bude.userserver.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;


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
            String s = JSON.toJSONString(teamService.getTeamListByUserId(userId, myId));
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
            String s = JSON.toJSONString(teamService.getTeamListByContestId(teamId, myId));
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
