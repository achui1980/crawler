package com.achui.crawler.spider.core;

import lombok.Data;
import org.openqa.selenium.WebDriver;

/**
 * @author portz
 * @date 2020/7/29 16:51
 */
@Data
public class Page {
    protected WebDriver webDriver;
    private String url;
    private String html;

    public Page() {

    }

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String processPage() throws Exception {
        return this.html;
    }

    public String processDetailPage() {
        return null;
    }
}
