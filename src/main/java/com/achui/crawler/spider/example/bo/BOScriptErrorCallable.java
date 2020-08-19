package com.achui.crawler.spider.example.bo;

import com.achui.crawler.spider.core.Spider;
import com.achui.crawler.spider.core.downloader.SeleniumDownloader;
import com.achui.crawler.spider.core.pipeline.ConsoleOutputPipeline;
import com.achui.crawler.spider.core.pipeline.FileOutputPipeline;
import com.achui.crawler.spider.example.github.GithubPageProcessor;

import java.util.concurrent.Callable;

/**
 * @author portz
 * @date 2020/8/19 17:18
 */
public class BOScriptErrorCallable implements Callable {
    @Override
    public Object call() throws Exception {
        new Spider()
                .processor(new BOScriptErrorProcessor())
                .scrapUrl("https://www9.qa.ehealthinsurance.com/bov2/login.html")
                .downloader(new SeleniumDownloader())
                .requireLogin(true)
                .addOutputPipeline(new ConsoleOutputPipeline())
                .run();
        return null;
    }
}
