package com.bude.collectengine.collect;

import com.bude.collectengine.repository.ContestNewsRepository;
import com.bude.collectengine.repository.NewsRepository;
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
import java.util.List;

@Log4j2
@Service
public class Collect5 {
    public String cid = "5";
    public String url = "https://cy.ncss.cn/information/dsdt";

    @Autowired
    NewsUtil newsUtil;


    @Scheduled(fixedRate = 1000*60*60)
    public void start() {
        try {

            String html = HttpUtilDownPage.sendGet(url);
            Document doc = Jsoup.parse(html);

            Elements e1 = doc.getElementsByClass("dynamic-message-box");
            for (int i = 0; i < e1.size(); i++) {
                try {
                    Element e2 = e1.get(i).getElementsByTag("a").get(0);
                    String title = e2.text();
                    String newsUrl =  "https://cy.ncss.cn" + e2.attr("href");
                    Element e3 = e1.get(i).getElementsByClass("pull-right").get(0);
                    Element e4 = e3.getElementsByTag("span").get(0);
                    String time = Arrays.asList(e4.text().split(" ")).get(1);
                    time = time + " 00:00:00";
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
