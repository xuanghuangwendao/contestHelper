package com.bude.collectengine.collect;

import com.bude.collectengine.entity.ContestNewsEntity;
import com.bude.collectengine.entity.NewsEntity;
import com.bude.collectengine.repository.ContestNewsRepository;
import com.bude.collectengine.repository.NewsRepository;
import com.bude.collectengine.utils.NewsUtil;
import com.bude.utils.model.NewsForList;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@Service
public class Collect1 {

    public String cid = "1";
    public String url = "http://www.mcm.edu.cn/html_cn/block/20ead73cbcf5a1c24b91947f98d7aac2.html";

    @Autowired
    NewsUtil newsUtil;

    @Scheduled(fixedRate = 1000*60*60)
    public void start() {
        try {
            Document doc = Jsoup.connect(url).get();
            Element e1 = doc.getElementById("divRightNodes");
            Elements e2 = e1.getElementsByClass("item");

            for (int i = 0; i < e2.size(); i++) {
                try {
                    Element element = e2.get(i);
                    String s2 = element.getElementsByTag("a").text();
                    String s3 = element.getElementsByTag("a").attr("href");
                    String title = s2;
                    String newsUrl = "http://www.mcm.edu.cn" + s3;
                    Timestamp timestamp;
                    Document doc1 = Jsoup.connect(newsUrl).get();
                    Element e3 = doc1.getElementById("divNodeAuthor");
                    String c = e3.text();
                    String[] ts = StringUtils.split(c, " ");
                    String time = ts[2] + " " + ts[3] + ":00";
                    timestamp = Timestamp.valueOf(time);

                    newsUtil.save(title, newsUrl, timestamp, cid);

                } catch (Exception e) {
                    log.error("i:" + i + " error");
                    log.error(e);
                }

            }
        } catch (Exception e) {
            log.error(e);
        }
    }

}
