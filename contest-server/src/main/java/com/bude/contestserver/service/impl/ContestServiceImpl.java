package com.bude.contestserver.service.impl;

import com.bude.contestserver.entity.ContestEntity;
import com.bude.contestserver.entity.ContestTeamEntityPK;
import com.bude.contestserver.entity.ContestUserEntity;
import com.bude.contestserver.entity.ContestUserEntityPK;
import com.bude.contestserver.repository.ContestRepository;
import com.bude.contestserver.repository.ContestTeamRepository;
import com.bude.contestserver.repository.ContestUserRepository;
import com.bude.contestserver.service.ContestService;
import com.bude.contestserver.service.NewsService;
import com.bude.contestserver.service.UserFeign;
import com.bude.contestserver.result.Result;
import com.bude.contestserver.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    UserFeign userFeign;

    @Autowired
    ContestUserRepository contestUserRepository;

    @Autowired
    ContestTeamRepository contestTeamRepository;

    @Autowired
    NewsService newsService;


    @Override
    public Result<ContestForPage> getContestById(int contestId, int myId) {

        Result<ContestForPage> result = new Result<>();
        Optional<ContestEntity> contest = contestRepository.findById(contestId);
        if (contest.isEmpty()) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("e", "no found");

        } else {
            ContestForPage page = new ContestForPage();
            page.setId(contest.get().getId());
            page.setName(contest.get().getName());
            page.setUrl(contest.get().getUrl());
            page.setOwnerId(contest.get().getOwner());
            page.setOwnerName(userFeign.getName((contest.get().getOwner())));
            page.setIntroduction(contest.get().getIntroduction());
            page.setLevel(contest.get().getLevel());
            page.setTag(contest.get().getTag());
            page.setFollow(checkUserFollow(contestId, myId).data);
            page.setNews(newsService.getNewsByContest(contestId).data);
            result.setData(page);
        }
        return result;
    }

    @Override
    public Result<List<ContestForList>> getContestListByName(String name, int myId) {
        Result<List<ContestForList>> result = new Result<>();
        List<ContestEntity> contests;
        if (StringUtils.isBlank(name)) {
            contests = (List<ContestEntity>) contestRepository.findAll();

        } else {
            contests = contestRepository.findAllByNameLike("%" + name + "%");
        }
        if (contests == null) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("e", "no found");
        } else {
            List<ContestForList> lists = new ArrayList<>();
            for(ContestEntity entity: contests) {
                ContestForList contest = new ContestForList();
                contest.setId(entity.getId());
                contest.setName(entity.getName());
                String title;
                Result<NewsForList> nl = newsService.getLastNews(entity.getId());
                if (!nl.state) {
                    title = "暂无公告";
                } else {
                    title = nl.data.getTitle();
                }
                contest.setLastNews(title);
                contest.setOwnerName(userFeign.getName((entity.getOwner())));
                contest.setTag(entity.getTag());
                lists.add(contest);
            }
            result.data = lists;
        }
        return result;
    }

    @Override
    public Result<List<ContestForList>> getAllContest(int myId) {
        Result<List<ContestForList>> result = new Result<>();
        List<ContestEntity> contests = (List<ContestEntity>) contestRepository.findAll();
        if (contests == null) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("e", "no found");
        } else {
            List<ContestForList> lists = new ArrayList<>();
            for(ContestEntity entity: contests) {
                ContestForList contest = new ContestForList();
                contest.setId(entity.getId());
                contest.setName(entity.getName());
                Result<NewsForList> last = newsService.getLastNews(entity.getId());
                if (!last.state) {
                    contest.setLastNews("暂无公告");
                } else {
                    contest.setLastNews(last.data.getTitle());

                }
                contest.setOwnerName(userFeign.getName((entity.getOwner())));
                contest.setTag(entity.getTag());
                lists.add(contest);
            }
            result.data = lists;
        }
        return result;
    }


    //TODO 独立到引擎
    @Override
    public Result<List<ContestForList>> getRecommend(int myId) {
        Result<List<ContestForList>> result = getAllContest(myId);
        if (result == null || !result.state || result.data == null) {
            return result;
        }
        String tag = userFeign.getTag(myId);
        List<String> tags = Arrays.asList(StringUtils.split(tag, ";"));
        for (ContestForList item: result.data) {
            List<String> t = Arrays.asList(StringUtils.split(item.getTag(), ";"));
            for(String one: t) {
                if (tags.contains(one)) {
                    item.setScore(item.getScore() + 2);
                }
                if (StringUtils.equals(one, "综合")) {
                    item.setScore(item.getScore() + 1);
                }
            }
        }
        result.data.sort(Comparator.comparing(ContestForList::getScore).reversed());
        return result;

    }

    @Override
    public Result<Boolean> checkUserFollow(int contestId, int myId) {
        ContestUserEntityPK pk = new ContestUserEntityPK();
        pk.setCid(contestId);
        pk.setUid(myId);
        if (contestUserRepository.findById(pk).isPresent()) {
            return new Result<>(true);
        } else {
            return new Result<>(false);
        }

    }

    @Override
    public Result<Boolean> checkTeamFollow(int contestId, int teamId) {
        ContestTeamEntityPK pk = new ContestTeamEntityPK();
        pk.setCid(contestId);
        pk.setTid(teamId);
        if (contestTeamRepository.findById(pk).isPresent()) {
            return new Result<>(true);
        } else {
            return new Result<>(false);
        }
    }

    @Override
    public Result<List<ContestForList>> getUserFollowList(int myId) {
        Result<List<ContestForList>> result = new Result<>();
        List<ContestUserEntity> contests = contestUserRepository.findAllByUidEquals(myId);
        if (contests == null) {
            result.setState(false);
            result.setData(null);
            result.getInfo().put("e", "no found");
        } else {
            List<ContestForList> lists = new ArrayList<>();
            for(ContestUserEntity cu: contests) {
                ContestForPage page = getContestById(cu.getCid(), myId).data;

                ContestForList contest = new ContestForList();
                contest.setId(page.getId());
                contest.setName(page.getName());
                Result<NewsForList> last = newsService.getLastNews(cu.getCid());
                if (!last.state) {
                    contest.setLastNews("暂无公告");
                } else {
                    contest.setLastNews(last.data.getTitle());
                }

                contest.setOwnerName(page.getOwnerName());
                contest.setTag(page.getTag());
                lists.add(contest);

            }
            result.data = lists;
        }
        return result;
    }

    @Override
    public Result<Boolean> followContest(int contestId, int myId) {
        ContestUserEntity entity = new ContestUserEntity();
        entity.setCid(contestId);
        entity.setUid(myId);
        contestUserRepository.save(entity);
        return new Result<>(true);
    }
    @Override
    public Result<Boolean> cFollowContest(int contestId, int myId) {
        ContestUserEntity entity = new ContestUserEntity();
        entity.setCid(contestId);
        entity.setUid(myId);
        contestUserRepository.delete(entity);
        return new Result<>(true);
    }

    @Override
    public Result<String> getName(int contestId) {
        Optional<ContestEntity> entity = contestRepository.findById(contestId);
        if (entity.isPresent()) {
            return new Result<>(entity.get().getName());
        } else {
            return new Result<>(false, "查询失败", Map.of("data", "no found"));
        }
    }

    @Override
    public Result<Boolean> create(ContestEntity contestEntity) {

        contestRepository.save(contestEntity);
        return new Result<>(true);
    }
}
