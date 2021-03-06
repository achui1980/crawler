package com.achui.crawler.spider.core;

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
    private Object metaData;
    private String url;
}
