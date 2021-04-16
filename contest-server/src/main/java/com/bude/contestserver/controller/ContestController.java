package com.bude.contestserver.controller;


import com.alibaba.fastjson.JSON;
import com.bude.contestserver.entity.ContestEntity;
import com.bude.contestserver.service.ContestService;
import com.bude.utils.result.Result;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
public class ContestController {

    @Autowired
    ContestService contestService;

    @RequestMapping("/page")
    public String byId(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value1 = body.get("contestId");
            String value2 = body.get("myId");
            int contestId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);
            String s = JSON.toJSONString(contestService.getContestById(contestId, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/getRecommend")
    public String getRecommend(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value2 = body.get("myId");
            int myId = Integer.parseInt(value2);


            String s = JSON.toJSONString(contestService.getRecommend(myId));
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
            log.info(body);
            String name = body.get("name");
            String v = body.get("myId");
            int myId = Integer.parseInt(v);

            String s = JSON.toJSONString(contestService.getContestListByName(name, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/userFollowList")
    public String userFollowList(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value = body.get("myId");
            int myId = Integer.parseInt(value);


            String s = JSON.toJSONString(contestService.getUserFollowList(myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }

    @RequestMapping("/followContest")
    public String followContest(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value1 = body.get("contestId");
            String value2 = body.get("myId");
            int contestId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);

            String s = JSON.toJSONString(contestService.followContest(contestId, myId));
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }
    @RequestMapping("/cFollowContest")
    public String cFollowContest(@RequestBody Map<String, String> body) {
        try {
            log.info(body);
            String value1 = body.get("contestId");
            String value2 = body.get("myId");
            int contestId = Integer.parseInt(value1);
            int myId = Integer.parseInt(value2);

            String s = JSON.toJSONString(contestService.cFollowContest(contestId, myId));
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
            String name = body.get("name");
            int owner = Integer.parseInt(body.get("myId"));
            String url = body.get("url");
            String introduction = body.get("introduction");
            String tag = body.get("tag");
            String level = body.get("level");
            ContestEntity contest = new ContestEntity();
            contest.setName(name);
            contest.setOwner(owner);
            contest.setUrl(url);
            contest.setIntroduction(introduction);
            contest.setTag(tag);
            contest.setLevel(level);
            return JSON.toJSONString(contestService.create(contest));
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }


    @RequestMapping("/getName")
    public String getName(@RequestParam Integer contestId) {
        try {

            if (contestId == null) {
                return "";
            }
            String s = contestService.getName(contestId).data;
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }
    @RequestMapping("/getNameById")
    public String getNameById(@RequestBody Map<String, String> body) {
        try {

            int contestId = Integer.parseInt(body.get("contestId"));
            Result<String> result = contestService.getName(contestId);
            String s = JSON.toJSONString(result);
            log.info(s);
            return s;
        } catch (Exception e) {
            log.error(e);
            return Result.ServerError();
        }
    }
}
