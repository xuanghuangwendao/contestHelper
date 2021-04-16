package com.bude.recommendengine.controller;


import com.alibaba.fastjson.JSON;
import com.bude.recommendengine.entity.ContestEntity;
import com.bude.recommendengine.service.ContestRecommendService;
import com.bude.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("recommend")
public class RecommendController {

    @Autowired
    ContestRecommendService contestRecommendService;

    @RequestMapping(value = "/byId")
    public String byId(@RequestBody Map<String, String> body) {
        try {
            String value = body.get("userId");
            int id = Integer.parseInt(value);
            if (id <= 0) {
                return Result.ParamError();
            }
            return JSON.toJSONString(contestRecommendService.getRecommendList(id));
        } catch (Exception e) {
            return Result.ServerError();
        }
    }

}
