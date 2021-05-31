package com.achui.crawler.spider.example.douban;

import com.achui.crawler.spider.core.PageHandler;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author portz
 * @date 2021/5/19 17:50
 */
public class DoubanDetailHandler implements PageHandler {
    private Queue requests = Lists.newLinkedList();

    @Override
    public RequestItem handle(SpiderPage page) throws Exception {
        WebClient webClient = page.getWebClient();
        HtmlPage htmlPage = webClient.getPage(page.getUrl());
        TimeUnit.SECONDS.sleep(2);
        String title = ((DomElement) htmlPage.getFirstByXPath("//*[@id=\"content\"]/h1/span[1]")).getTextContent();
        System.out.println("detail" + title);
        RequestItem requestItem = new RequestItem();
        requestItem.put("titile-detail", title);
        requests.offer(requestItem);
        return requestItem;
    }

    @Override
    public Queue getRequestQueue() {
        return requests;
    }
}
