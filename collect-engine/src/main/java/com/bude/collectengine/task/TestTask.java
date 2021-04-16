package com.bude.collectengine.task;

import com.bude.collectengine.collect.Collect1;
import com.bude.collectengine.collect.Collect2;
import com.bude.collectengine.collect.Collect3;
import com.bude.collectengine.utils.HttpUtilDownPage;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@Component
public class TestTask {

    @Autowired
    Collect1 collect1;

    @Autowired
    Collect2 collect2;

    @Autowired
    Collect3 collect3;

    @Scheduled(fixedRate = 100000)
    public void run() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        log.info(timestamp);

//        collect3.start();
    }
}
