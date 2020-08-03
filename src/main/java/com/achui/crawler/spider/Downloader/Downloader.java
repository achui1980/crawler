package com.achui.crawler.spider.Downloader;

import com.achui.crawler.spider.Request;
import com.achui.crawler.spider.page.Page;

/**
 * @author portz
 * @date 2020/7/31 17:04
 */
public interface Downloader {
    Page download(Request request);
}
