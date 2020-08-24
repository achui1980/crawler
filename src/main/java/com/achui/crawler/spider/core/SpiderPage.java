package com.achui.crawler.spider.core;

import com.achui.crawler.spider.core.RequestItem;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * @author portz
 * @date 2020/7/31 16:52
 */
public class SpiderPage {
    private String url;
    private RequestItem requestItem = new RequestItem();
    private List<RequestItem> itemList = Lists.newArrayList();
    @Getter
    @Setter
    private WebDriver webDriverEngine;
    @Getter
    @Setter
    private WebClient webClient;
    private boolean pagination;

    public void putField(String key, String field) {
        this.requestItem.put(key, field);
    }

    public void putToList(RequestItem requestItem) {
        this.itemList.add(requestItem);
    }
}