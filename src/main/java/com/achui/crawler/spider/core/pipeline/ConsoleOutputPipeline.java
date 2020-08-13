package com.achui.crawler.spider.core.pipeline;

import com.achui.crawler.spider.core.RequestItem;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author portz
 * @date 2020/8/12 18:04
 */
@Slf4j
public class ConsoleOutputPipeline implements OutputPipeline {
    @Override
    public void output(RequestItem requestItem) {
        Map<String, String> item = requestItem.getItem();
        item.entrySet().forEach(entry -> {
            log.info("key:{}, value:{}", entry.getKey(), entry.getValue());
        });
    }
}
