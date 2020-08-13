package com.achui.crawler.spider.core;

/**
 * @author portz
 * @date 2020/7/31 15:04
 */
public interface PageProcessor extends IRequestQueue {
    void login(SpiderPage page);

    void processMainPage(SpiderPage page) throws Exception;
}
