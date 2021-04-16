package com.bude.collectengine.collect;

import com.bude.collectengine.utils.HttpUtilDownPage;
import com.bude.collectengine.utils.NewsUtil;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;

@Log4j2
@Service
public class Collect8 {
    public String cid = "8";
    public String url = "http://nuedc.xjtu.edu.cn/index/index/lists/catid/6.html";

    @Autowired
    NewsUtil newsUtil;


    @Scheduled(fixedRate = 1000*60*60)
    public void start() {
        try {

            Document doc = Jsoup.connect(url).get();

            Elements e1 = doc.getElementsByClass("text-item");
            for (int i = 0; i < e1.size(); i++) {
                try {
                    Element e2 = e1.get(i).getElementsByTag("a").get(0);
                    String title = e2.text();
                    String newsUrl = e2.attr("href");
                    Element e3 = e1.get(i).getElementsByClass("text-item-time").get(0);
                    String time = e3.text() + " 00:00:00";
                    Timestamp timestamp = Timestamp.valueOf(time);
                    newsUtil.save(title,newsUrl, timestamp, cid);

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
