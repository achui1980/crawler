package com.achui.crawler.spider.core;

/**
 * @author portz
 * @date 2020/7/31 15:04
 */
public interface PageProcessor {
    public void login(com.achui.crawler.spider.page.Page page);

    public void process(com.achui.crawler.spider.page.Page page) throws Exception;
}
