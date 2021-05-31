package com.achui.crawler.spider.example.novel;

import com.achui.crawler.spider.OutputPipelineException;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.pipeline.OutputPipeline;
import com.achui.crawler.spider.example.novel.domain.NovelMetaData;
import lombok.extern.slf4j.Slf4j;

/**
 * @author portz
 * @date 2021/5/31 15:15
 */
@Slf4j
public class NovelOutputPipeline implements OutputPipeline {
    @Override
    public void output(RequestItem requestItem) throws OutputPipelineException {
        NovelMetaData metaData = (NovelMetaData) requestItem.getItem().get("novel");
        log.info(metaData.toString());
    }
}
