package com.achui.crawler.spider.core;

import com.google.common.collect.Lists;

import java.util.Queue;

/**
 * @author portz
 * @date 2020/8/24 16:53
 */
public abstract class AbstractPageProcessor implements PageProcessor {
    private Queue requests = Lists.newLinkedList();

    @Override
    public Queue getRequestQueue() {
        return requests;
    }
}
