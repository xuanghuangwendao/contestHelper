package com.bude.recommendengine.cache;

import com.bude.recommendengine.entity.ContestEntity;
import com.bude.recommendengine.repository.ContestRepository;
import com.bude.utils.model.ContestForList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ContestCache {

    public static Map<String, List<Info>> tagMap;

    public static long time;

    public static long dist = 60*60*1000;

    public static long initDist = 12*60*60*1000;

    public static long initTime;

    public static ContestCache contestCache;

    @Autowired
    ContestService contestService;

    @Autowired
    ContestRepository contestRepository;


    public static ContestCache getInstance() {
        if (contestCache == null) {
            contestCache = new ContestCache();
            contestCache.init();
            initTime = contestCache.updateTime();
            return contestCache;
        }
        return contestCache;
    }

    public long updateTime() {
        time = System.currentTimeMillis();
        return time;
    }
    public boolean needUpdate(Info info) {
        if (initTime - updateTime() > initDist) {
            init();
        }
        if (time - info.getTimeStamp() > dist) {
            info.timeStamp = time;
            return true;
        } else {
            return false;
        }
    }

    public void init() {
        tagMap = new HashMap<>();
        List<ContestEntity> list = (List<ContestEntity>) contestRepository.findAll();
        for (ContestEntity entity: list) {
            String tag = entity.getTag();
            tagMap.computeIfAbsent(tag, k -> new ArrayList<>());
            tagMap.get(tag).add(getFromEntity(entity));
        }
    }

    public List<ContestForList> query(String tag) {


        List<Info> infos = tagMap.get(tag);
        List<ContestForList> lists = new ArrayList<>();
        if (infos == null) {
            infos = new ArrayList<>();
        }
        for (Info info: infos) {
            if(needUpdate(info)) {
                if (!info.update()) {
                    infos.remove(info);
                }
            }
        }
        for (Info info: infos) {
            lists.add(getFromInfo(info));
        }
        return lists;

    }




    Info getFromEntity(ContestEntity entity) {
        Info info = new Info();
        info.id = entity.getId();
        info.name = entity.getName();
        info.timeStamp = updateTime();
        return info;

    }
    ContestForList getFromInfo(Info info) {
        ContestForList entity = new ContestForList();
        entity.setId(info.id);
        entity.setName(info.name);
        return entity;
    }


    @Data
    class Info {
        private int id;

        private String name;

        private long timeStamp;

        public boolean update() {
            Optional<ContestEntity> entity = contestRepository.findById(id);
            if (entity.isPresent()) {
                name = entity.get().getName();
                timeStamp = updateTime();
                return true;
            } else {
                return false;
            }
        }

    }
}
