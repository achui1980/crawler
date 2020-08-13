package com.achui.crawler.spider.example.github;

import com.achui.crawler.spider.core.PageHandler;
import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import com.google.common.collect.Lists;

import java.util.Queue;

/**
 * @author portz
 * @date 2020/8/11 16:36
 */
public class ProjectDetailHanlder implements PageHandler {
    @Override
    public RequestItem handle(SpiderPage page) {
        System.out.println("handler detail page:" + page.getWebDriverEngine().getTitle());
        RequestItem requestItem = new RequestItem();
        requestItem.put("title", page.getWebDriverEngine().getTitle());
        return requestItem;
    }

    @Override
    public Queue<Request> getRequestQueue() {
        return Lists.newLinkedList();
    }
}
