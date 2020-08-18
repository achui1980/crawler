package com.achui.crawler.spider.core;

import com.achui.crawler.spider.core.downloader.Downloader;
import com.achui.crawler.spider.core.downloader.SeleniumDownloader;
import com.achui.crawler.spider.core.pipeline.ConsoleOutputPipeline;
import com.achui.crawler.spider.core.pipeline.OutputPipeline;
import com.achui.crawler.spider.example.github.GithubPageProcessor;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Queue;

/**
 * @author portz
 * @date 2020/7/31 15:00
 */
public class Spider {
    private PageProcessor pageProcessor;
    @Setter
    private List<String> urls;

    private List<Request> requests;

    private List<OutputPipeline> outputPipeLines;

    private Downloader downloader;

    @Setter
    private boolean requireLogin = true;

    public Spider() {
        this.outputPipeLines = Lists.newArrayList();
        this.urls = Lists.newArrayList();
    }

    public Spider addOutputPipeline(OutputPipeline outputPipeline) {
        this.outputPipeLines.add(outputPipeline);
        return this;
    }

    public Spider scrapUrl(String scrapUrl) {
        this.urls.add(scrapUrl);
        return this;
    }

    public Spider processor(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
        return this;
    }

    public Spider downloader(Downloader downloader) {
        this.downloader = downloader;
        return this;
    }

    public Spider requireLogin(boolean requireLogin) {
        this.requireLogin = requireLogin;
        return this;
    }

    public void run() throws Exception {
        //SeleniumDownloader downloader = new SeleniumDownloader();
        Request request = new Request();
        request.setUrl(this.urls.get(0));
        request.setCallback(this.pageProcessor);
        SpiderPage page = this.downloader.download(request);
        if (requireLogin) {
            request.getCallback().login(page);
        }
        request.getCallback().processMainPage(page);
        Queue<Request> queue = pageProcessor.getRequestQueue();
        while (!CollectionUtils.isEmpty(queue)) {
            Request nextRequest = queue.poll();
            SpiderPage nextPage = downloader.download(nextRequest);
            RequestItem requestItem = nextRequest.getPageHandler().handle(nextPage);
            if (requestItem != null) {
                this.outputPipeLines.forEach(outputPipeline -> outputPipeline.output(requestItem));
            }
            queue.addAll(nextRequest.getPageHandler().getRequestQueue());
        }
        page.getWebDriverEngine().quit();
        page.getWebDriverEngine().close();

    }

    public static void main(String[] args) throws Exception {
        new Spider()
                .processor(new GithubPageProcessor())
                .requireLogin(true)
                .downloader(new SeleniumDownloader())
                .scrapUrl("https://github.com/login")
                .addOutputPipeline(new ConsoleOutputPipeline())
                .run();
    }
}
