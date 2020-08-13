package com.achui.crawler.spider.core.downloader;


import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.SpiderPage;

/**
 * @author portz
 * @date 2020/7/31 17:04
 */
public interface Downloader {
    SpiderPage download(Request request);
}
