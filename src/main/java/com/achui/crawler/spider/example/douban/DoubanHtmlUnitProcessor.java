package com.achui.crawler.spider.example.douban;

import com.achui.crawler.spider.core.PageProcessor;
import com.achui.crawler.spider.core.SpiderPage;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;

import java.nio.charset.Charset;
import java.util.Queue;

public class DoubanHtmlUnitProcessor implements PageProcessor {
    @Override
    public void login(SpiderPage page) throws Exception {

    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebClient webClient = page.getWebClient();
        Page scrapPage = page.getScrapPage();
        String html = scrapPage.getWebResponse().getContentAsString(Charset.forName("UTF-8"));
        int offset = 0;
        while (true) {
            String url = "https://movie.douban.com/top250?start=" + offset + "&filter=";
            int code = webClient.getPage(url).getWebResponse().getStatusCode();
            System.out.println("========" + code + " offset:" + offset);
            offset += 25;
            if (offset >= 250) {
                break;
            }
        }
        //System.out.println(Jsoup.parse(html).title());
    }

    @Override
    public Queue getRequestQueue() {
        return Lists.newLinkedList();
    }
}
