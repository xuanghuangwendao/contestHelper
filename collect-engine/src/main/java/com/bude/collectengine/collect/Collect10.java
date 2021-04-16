package com.bude.collectengine.collect;

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

@Log4j2
@Service
public class Collect10 {
    public String cid = "10";
    public String url = "http://uchallenge.unipus.cn/2018/news/";

    @Autowired
    NewsUtil newsUtil;


    @Scheduled(fixedRate = 1000*60*60)
    public void start() {
        try {

            Document doc = Jsoup.connect(url).get();

            Elements e1 = doc.getElementsByClass("text-list");
            Elements e2 = e1.get(0).getElementsByTag("li");
            for (int i = 0; i < e2.size(); i++) {
                try {
                    Element e3 = e2.get(i);
                    Element e4 = e3.getElementsByTag("a").get(0);
                    String newsUrl = e4.attr("href");
                    newsUrl = "http://uchallenge.unipus.cn/" + newsUrl.substring(6);
                    String title = e4.getElementsByClass("title").get(0).text();
                    String time = e4.getElementsByClass("time").get(0).text();
                    time = time.replace('/', '-') + " 00:00:00";
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
