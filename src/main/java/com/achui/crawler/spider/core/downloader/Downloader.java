package com.achui.crawler.spider.core.downloader;


import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.SpiderPage;

/**
 * @author portz
 * @date 2020/7/31 17:04
 */
public interface Downloader {
    /**
     * 页面下载器，用户下载页面内容
     * @param request
     * @return
     * @throws Exception
     */
    SpiderPage download(Request request) throws Exception;
}
