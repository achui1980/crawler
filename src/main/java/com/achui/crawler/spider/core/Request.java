package com.achui.crawler.spider.core;

import com.achui.crawler.spider.core.PageHandler;
import com.achui.crawler.spider.core.PageProcessor;
import lombok.Data;

/**
 * @author portz
 * @date 2020/8/3 15:51
 */
@Data
public class Request {
    private PageProcessor callback;
    private PageHandler pageHandler;
    private boolean openInNewTab = false;
    private String callbackMethod;
    private String url;
}
