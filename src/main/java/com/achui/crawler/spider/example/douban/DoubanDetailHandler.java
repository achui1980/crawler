package com.achui.crawler.spider.example.douban;

import com.achui.crawler.spider.core.PageHandler;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Queue;

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
        List<DomElement> list = htmlPage.getByXPath("//*[@id=\"content\"]/div/div[1]/ol/li");
        return null;
    }

    @Override
    public Queue getRequestQueue() {
        return requests;
    }
}
