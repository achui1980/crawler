package com.achui.crawler.spider.core.pipeline;

import com.achui.crawler.spider.OutputPipelineException;
import com.achui.crawler.spider.core.RequestItem;

/**
 * @author portz
 * @date 2020/8/12 17:53
 */
public interface OutputPipeline {

    /**
     * 请求对象输出器
     * @param requestItem
     * @throws OutputPipelineException
     */
    void output(RequestItem requestItem) throws OutputPipelineException;
}
