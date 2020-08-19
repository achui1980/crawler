package com.achui.crawler.spider.core;

/**
 * @author portz
 * @date 2020/7/31 15:04
 */
public interface PageProcessor extends IProcessResultQueue {
    void login(SpiderPage page) throws Exception;

    void processMainPage(SpiderPage page) throws Exception;
}
