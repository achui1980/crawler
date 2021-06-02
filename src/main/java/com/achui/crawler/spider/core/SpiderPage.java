package com.achui.crawler.spider.core;

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
    @Getter
    @Setter
    private String url;
    private RequestItem requestItem = new RequestItem();
    @Setter
    @Getter
    private Object metaData;
    private List<RequestItem> itemList = Lists.newArrayList();
    /**
     * Used for selenium engine
     */
    @Getter
    @Setter
    private WebDriver webDriverEngine;
    /**
     * Used for Html Handler
     */
    @Getter
    @Setter
    private WebClient webClient;
    private boolean pagination;

    public void putField(String key, Object field) {
        this.requestItem.put(key, field);
    }

    public void putToList(RequestItem requestItem) {
        this.itemList.add(requestItem);
    }
}