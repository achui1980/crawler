package com.achui.crawler.spider.core;

import com.achui.crawler.spider.core.downloader.Downloader;
import com.achui.crawler.spider.core.downloader.HtmlunitDownloader;
import com.achui.crawler.spider.core.pipeline.ConsoleOutputPipeline;
import com.achui.crawler.spider.core.pipeline.OutputPipeline;
import com.achui.crawler.spider.example.douban.DoubanHtmlUnitProcessor;
import com.achui.crawler.spider.example.novel.BiqugeProcessor;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Queue;
import java.util.logging.Level;

/**
 * 将每个页面的的内容都缓存到本地队列中，然后队列里面读取每个请求进行处理，通过内容下载器进行内容过滤
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
        Queue queue = pageProcessor.getRequestQueue();
        while (!CollectionUtils.isEmpty(queue)) {
            Object processResult = queue.poll();
            if (processResult instanceof Request) {
                Request nextRequest = (Request) processResult;
                SpiderPage nextPage = downloader.download((Request) processResult);
                nextRequest.getPageHandler().handle(nextPage);
                queue.addAll(nextRequest.getPageHandler().getRequestQueue());
            } else if (processResult instanceof RequestItem) {
                RequestItem requestItem = (RequestItem) processResult;
                this.outputPipeLines.forEach(outputPipeline -> outputPipeline.output(requestItem));
            }
        }
        if (page.getWebDriverEngine() != null) {
            page.getWebDriverEngine().close();
            page.getWebDriverEngine().quit();
        }

    }

    public static void main(String[] args) throws Exception {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        new Spider()
                .processor(new BiqugeProcessor())
                .requireLogin(false)
                .downloader(new HtmlunitDownloader())
                .scrapUrl("https://movie.douban.com/top250")
                .addOutputPipeline(new ConsoleOutputPipeline())
                .run();
    }
}
