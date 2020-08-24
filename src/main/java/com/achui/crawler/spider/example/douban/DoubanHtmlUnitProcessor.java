package com.achui.crawler.spider.example.douban;

import com.achui.crawler.spider.core.PageProcessor;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Queue;

public class DoubanHtmlUnitProcessor implements PageProcessor {
    private Queue requests = Lists.newLinkedList();
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
            HtmlPage content = webClient.getPage(url);
            List<DomElement> list = content.getByXPath("//*[@id=\"content\"]/div/div[1]/ol/li");
            for (DomElement doc : list) {
                String title = ((DomElement) doc.getFirstByXPath("div/div[2]/div[1]/a/span[1]")).getTextContent();
                String rate = ((DomElement) doc.getFirstByXPath("div/div[2]/div[2]/div/span[2]")).getTextContent();
                RequestItem requestItem = new RequestItem();
                requestItem.put("title", title);
                requestItem.put("rate", rate);
                System.out.println(title + ":" + rate);
                requests.offer(requestItem);
            }
            offset += 25;
            if (offset >= 250) {
                break;
            }
        }
    }

    @Override
    public Queue getRequestQueue() {
        return requests;
    }
}
