package com.achui.crawler.spider.core;

import com.achui.crawler.spider.Downloader.SeleniumDownloader;
import com.achui.crawler.spider.Request;
import com.achui.crawler.spider.page.Page;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @author portz
 * @date 2020/7/31 15:00
 */
public class Spider {
    private PageProcessor pageProcessor;
    @Setter
    private List<String> urls;

    @Setter
    private boolean requireLogin = true;

    public Spider() {

    }

    public Spider(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
    }

    public void run() throws Exception {
        SeleniumDownloader downloader = new SeleniumDownloader();
        Request request = new Request();
        request.setUrl(this.urls.get(0));
        request.setCallback(this.pageProcessor);
        Page page = downloader.download(request);
        if (requireLogin) {
            request.getCallback().login(page);
        }
        request.getCallback().process(page);
    }

    public static void main(String[] args) throws Exception {
        Spider spider = new Spider();
        spider.pageProcessor = new GithubPageProcessor();
        spider.setUrls(Arrays.asList("https://github.com/login"));
        spider.run();
    }
}
