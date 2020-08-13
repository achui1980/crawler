package com.achui.crawler.spider.core.page;

import com.achui.crawler.spider.core.RequestItem;
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
    private boolean pagination;

    public void putField(String key, String field) {
        this.requestItem.put(key, field);
    }

    public void putToList(RequestItem requestItem) {
        this.itemList.add(requestItem);
    }
}