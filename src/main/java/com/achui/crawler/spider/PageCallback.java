package com.achui.crawler.spider;

import com.achui.crawler.spider.page.Page;

/**
 * @author portz
 * @date 2020/8/3 16:41
 */
public interface PageCallback {

    void prcessCallbackPage(Page page);
}
