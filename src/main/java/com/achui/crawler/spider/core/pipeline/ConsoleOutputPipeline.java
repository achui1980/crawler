package com.achui.crawler.spider.core.pipeline;

import com.achui.crawler.spider.core.RequestItem;
import lombok.extern.slf4j.Slf4j;

/**
 * @author portz
 * @date 2020/8/12 18:04
 */
@Slf4j
public class ConsoleOutputPipeline implements OutputPipeline {
    @Override
    public void output(RequestItem requestItem) {
        log.info(requestItem.toString());
    }
}
