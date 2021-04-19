package com.bude.teamserver.controller;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/chaoren")
public class ChaoRenController {

    @Autowired
    ChaorenRepository repository;

    @RequestMapping("/get/{qq}")
    public String get(@PathVariable String qq) {
        try {
            Date date = new Date(System.currentTimeMillis());
            String s = "";
            s = s +date.getDate() + "-" + date.getMonth() + "-" + date.getDay();
            ChaorenEntity entity = new ChaorenEntity();
            entity.setTime(s);
            entity.setQq(qq);
            String data = "";
            List<ChaorenEntity> list = repository.findAllByTimeEqualsAndQqEquals(s, qq);
            if (list.isEmpty()) {
                data = String.valueOf((int)(Math.random() * 100));
                entity.setTime(data);
                repository.save(entity);
            } else {
                data = list.get(0).getData();
            }
            return data;
        } catch (Exception e) {
            String s = e.toString();
            return s;
        }
    }

}
