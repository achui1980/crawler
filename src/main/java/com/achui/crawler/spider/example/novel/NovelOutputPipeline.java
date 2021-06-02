package com.achui.crawler.spider.example.novel;

import com.achui.crawler.spider.OutputPipelineException;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.pipeline.OutputPipeline;
import com.achui.crawler.spider.example.novel.domain.Chapter;
import com.achui.crawler.spider.example.novel.domain.NovelMetaData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;

/**
 * @author portz
 * @date 2021/5/31 15:15
 */
@Slf4j
public class NovelOutputPipeline implements OutputPipeline {
    @Override
    public void output(RequestItem requestItem) throws OutputPipelineException {
        NovelMetaData novelMetaData = (NovelMetaData) requestItem.getItem().get("novel");
        if (novelMetaData != null) {
            log.info(novelMetaData.toString());
        }
        Chapter chapter = (Chapter) requestItem.getItem().get("chapter");
        try {
            if (chapter != null) {
                URI uri = new URI(chapter.getLink());
                String link = uri.getPath().replace("/", "_");
                FileUtils.write(new File("C://tmp/novel/" + chapter.getName() + "/" + link), chapter.getContent(), Charset.defaultCharset());
            }
        } catch (Exception ex) {
            log.error("error", ex);
        }

    }
}
