package com.achui.crawler.spider.example.douban;

import com.achui.crawler.spider.core.AbstractPageProcessor;
import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.Spider;
import com.achui.crawler.spider.core.SpiderPage;
import com.achui.crawler.spider.core.downloader.HtmlunitDownloader;
import com.achui.crawler.spider.core.pipeline.ConsoleOutputPipeline;
import com.achui.crawler.spider.example.novel.BiqugeProcessor;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;
import java.util.logging.Level;

public class DoubanHtmlUnitProcessor extends AbstractPageProcessor {
    @Override
    public void login(SpiderPage page) throws Exception {

    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebClient webClient = page.getWebClient();
        int offset = 0;
        while (true) {
            String url = "https://movie.douban.com/top250?start=" + offset + "&filter=";
            HtmlPage content = webClient.getPage(url);
            List<DomElement> list = content.getByXPath("//*[@id=\"content\"]/div/div[1]/ol/li");
            //*[@id="content"]/div/div[1]/ol/li[1]/div/div[2]/div[1]/a
            for (DomElement doc : list) {
                String title = ((DomElement) doc.getFirstByXPath("div/div[2]/div[1]/a/span[1]")).getTextContent();
                String rate = ((DomElement) doc.getFirstByXPath("div/div[2]/div[2]/div/span[2]")).getTextContent();
                String detailLink = ((DomElement) doc.getFirstByXPath("div/div[2]/div[1]/a")).getAttribute("href");

                RequestItem requestItem = new RequestItem();
                requestItem.put("title", title);
                requestItem.put("rate", rate);
                System.out.println(title + ":" + detailLink);
                //getRequestQueue().offer(requestItem);
                Request nextRequest = new Request();
                nextRequest.setUrl(detailLink);
                nextRequest.setPageHandler(new DoubanDetailHandler());
                super.getRequestQueue().offer(nextRequest);
            }
            offset += 1;
            if (offset >= 0) {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        new Spider()
                .processor(new DoubanHtmlUnitProcessor())
                .requireLogin(false)
                .downloader(new HtmlunitDownloader())
                .scrapUrl("https://movie.douban.com/top250")
                .addOutputPipeline(new ConsoleOutputPipeline())
                .run();
    }
}
