package com.achui.crawler.spider.core;


/**
 * @author portz
 * @date 2020/8/11 9:56
 */
public interface PageHandler extends IProcessResultQueue {
    RequestItem handle(SpiderPage page);
}
