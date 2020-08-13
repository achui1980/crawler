package com.achui.crawler.spider.example.github;

import com.achui.crawler.spider.core.Spider;
import com.achui.crawler.spider.core.pipeline.ConsoleOutputPipeline;

import java.util.concurrent.Callable;

/**
 * @author portz
 * @date 2020/8/13 14:25
 */
public class GithubPageCallable implements Callable {
    @Override
    public Object call() throws Exception {
        new Spider()
                .processor(new GithubPageProcessor())
                .scrapUrl("https://github.com/login")
                .addOutputPipeline(new ConsoleOutputPipeline())
                .run();
        return null;
    }
}
