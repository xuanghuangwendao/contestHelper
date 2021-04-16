package com.bude.collectengine.collect;

import com.bude.collectengine.entity.ContestNewsEntity;
import com.bude.collectengine.entity.NewsEntity;
import com.bude.collectengine.repository.ContestNewsRepository;
import com.bude.collectengine.repository.NewsRepository;
import com.bude.collectengine.utils.NewsUtil;
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

@Log4j2
@Service
public class Collect2 {

    public String cid = "2";
    public String url = "http://www.chinaneccs.cn/list/neccsnews";

    @Autowired
    NewsUtil newsUtil;

    @Scheduled(fixedRate = 1000*60*60)
    public void start() {
        try {
            Document doc = Jsoup.connect(url).get();
            Element e1 = doc.getElementsByClass("item-ul level-icon").get(0);
            Elements e2 = e1.getElementsByClass("clearfix");
            for (int i = 0; i < e2.size(); i++) {
                try {
                    Element e3 = e2.get(i);
                    Element e4 = e3.getElementsByClass("text").get(0);
                    String title = e4.attr("title");
                    String newsUrl = e4.attr("href");
                    newsUrl = "http://www.chinaneccs.cn" + newsUrl;
                    String time = e3.getElementsByClass("time").get(0).text();
                    time = time + " 00:00:00";
                    Timestamp timestamp = Timestamp.valueOf(time);

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
