package com.achui.crawler.spider.example.novel;

import com.achui.crawler.spider.core.PageHandler;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import com.achui.crawler.spider.example.novel.domain.Chapter;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;

import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author portz
 * @date 2021/5/31 16:16
 * 下载小说内容
 */
public class BiqugeContentHandler implements PageHandler {
    private Queue requests = Lists.newLinkedList();

    @Override
    public RequestItem handle(SpiderPage page) throws Exception {
        WebClient webClient = page.getWebClient();
        HtmlPage htmlPage = webClient.getPage(page.getUrl());
        String content = htmlPage.getElementById("nr").asXml();
        String chapterName = htmlPage.getElementById("_bqgmb_h1").getTextContent();
        String nextPageLink = htmlPage.getElementById("pb_next").getAttribute("href");
        String regx = "_\\d{1,2}.html$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(nextPageLink);
        System.out.println(page.getUrl());
        //获取页数
        HtmlPage nextPage;
        while (matcher.find()) {
            nextPage = webClient.getPage(BiqugeProcessor.HOST + nextPageLink);
            nextPageLink = nextPage.getElementById("pb_next").getAttribute("href");
            matcher = pattern.matcher(nextPageLink);
            content += nextPage.getElementById("nr").asXml();
        }

        RequestItem requestItem = new RequestItem();
        Chapter chapter = Chapter.builder()
                .content(content)
                .link(page.getUrl())
                .name(chapterName.trim())
                .build();
        requestItem.put("chapter", chapter);
        requestItem.put("metaData", page.getMetaData());
        this.requests.offer(requestItem);
        return requestItem;
    }

    @Override
    public Queue getRequestQueue() {
        return requests;
    }
}
