package com.achui.crawler.spider.example.douban;

import com.achui.crawler.spider.core.AbstractPageProcessor;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public class DoubanHtmlUnitProcessor extends AbstractPageProcessor {
    @Override
    public void login(SpiderPage page) throws Exception {

    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebClient webClient = page.getWebClient();
        int offset = 0;
        while (true) {
            String url = "https://movie.douban.com/top250?start=" + offset + "&filter=";
            HtmlPage content = webClient.getPage(url);
            List<DomElement> list = content.getByXPath("//*[@id=\"content\"]/div/div[1]/ol/li");
            for (DomElement doc : list) {
                String title = ((DomElement) doc.getFirstByXPath("div/div[2]/div[1]/a/span[1]")).getTextContent();
                String rate = ((DomElement) doc.getFirstByXPath("div/div[2]/div[2]/div/span[2]")).getTextContent();
                RequestItem requestItem = new RequestItem();
                requestItem.put("title", title);
                requestItem.put("rate", rate);
                System.out.println(title + ":" + rate);
                getRequestQueue().offer(requestItem);
            }
            offset += 25;
            if (offset >= 250) {
                break;
            }
        }
    }
}
