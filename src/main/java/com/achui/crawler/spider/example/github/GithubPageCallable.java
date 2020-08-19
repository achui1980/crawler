package com.achui.crawler.spider.example.github;

import com.achui.crawler.spider.core.Spider;
import com.achui.crawler.spider.core.downloader.SeleniumDownloader;
import com.achui.crawler.spider.core.pipeline.ConsoleOutputPipeline;
import com.achui.crawler.spider.core.pipeline.FileOutputPipeline;

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
                .downloader(new SeleniumDownloader())
                .requireLogin(true)
                .addOutputPipeline(new ConsoleOutputPipeline())
                .addOutputPipeline(new FileOutputPipeline("c:/tmp/github_port.txt"))
                .run();
        return null;
    }
}
