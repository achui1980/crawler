package com.achui.crawler.spider.core.pipeline;

import com.achui.crawler.spider.core.RequestItem;

/**
 * @author portz
 * @date 2020/8/12 17:53
 */
public interface OutputPipeline {

    void output(RequestItem requestItem);
}
