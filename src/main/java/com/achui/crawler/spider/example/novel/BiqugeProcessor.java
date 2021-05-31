package com.achui.crawler.spider.example.novel;

import com.achui.crawler.spider.core.AbstractPageProcessor;
import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.Spider;
import com.achui.crawler.spider.core.SpiderPage;
import com.achui.crawler.spider.core.downloader.HtmlunitDownloader;
import com.achui.crawler.spider.core.pipeline.ConsoleOutputPipeline;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;
import java.util.logging.Level;

/**
 * @author portz
 * @date 2021/5/19 16:08
 */
public class BiqugeProcessor extends AbstractPageProcessor {
    @Override
    public void login(SpiderPage page) throws Exception {

    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebClient webClient = page.getWebClient();
        int offset = 1;
        while (true) {
            String url = "https://m.shuquge.com/store/allvisit-" + offset + ".html";
            HtmlPage content = webClient.getPage(url);
            List<DomElement> list = content.getByXPath("//p[@class='line']");
            for (DomElement doc : list) {
                String detailPage = ((DomElement) doc.getFirstByXPath("a[@class='blue']")).getAttribute("href");
                Request nextRequest = new Request();
                nextRequest.setUrl("https://m.shuquge.com" + detailPage);
                nextRequest.setPageHandler(new BiqugeDetailHandler());
                super.getRequestQueue().offer(nextRequest);
            }
            offset += 1;
            if (offset >= 1) {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

        new Spider()
                .processor(new BiqugeProcessor())
                .requireLogin(false)
                .downloader(new HtmlunitDownloader())
                .scrapUrl("https://m.shuquge.com/store/allvisit-1.html")
                .addOutputPipeline(new ConsoleOutputPipeline())
                .run();
    }
}
