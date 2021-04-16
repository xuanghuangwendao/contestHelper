package com.bude.collectengine.collect;

import com.bude.collectengine.entity.ContestNewsEntity;
import com.bude.collectengine.entity.NewsEntity;
import com.bude.collectengine.repository.ContestNewsRepository;
import com.bude.collectengine.repository.NewsRepository;
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
public class Collect3 {
    public String cid = "3";
    public String url = "http://www.tiaozhanbei.net/tzb/notice";


    @Autowired
    NewsUtil newsUtil;


    @Scheduled(fixedRate = 1000*60*60)
    public void start() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements e1 = doc.getElementsByClass("clearfix mbC mtC");
            for (int i = 0; i < e1.size(); i++) {
                try {

                    Element e2 = e1.get(i).getElementsByClass("col-1-5").get(0);
                    String title = e2.attr("title");
                    String newsUrl = "http://www.tiaozhanbei.net" + e2.attr("href");
                    String time = e1.get(i).getElementsByClass("info txtB").text();
                    List<String> list = Arrays.asList(time.split("\\."));
                    String s = list.get(0) + "-" + list.get(1) + "-" + list.get(2) + " 00:00:00";
                    Timestamp timestamp = Timestamp.valueOf(s);
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
