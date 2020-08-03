package com.achui.crawler.spider;

import com.achui.crawler.spider.core.PageProcessor;
import lombok.Data;

/**
 * @author portz
 * @date 2020/8/3 15:51
 */
@Data
public class Request {
    private PageProcessor callback;
    private String callbackMethod;
    private String url;
}
