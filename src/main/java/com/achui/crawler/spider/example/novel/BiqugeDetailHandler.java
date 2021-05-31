package com.achui.crawler.spider.example.novel;

import com.achui.crawler.spider.core.PageHandler;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author portz
 * @date 2021/5/20 15:41
 */
public class BiqugeDetailHandler implements PageHandler {
    private Queue requests = Lists.newLinkedList();
    private Set<String> chapters = new HashSet<>();
    private final static String HOST = "https://m.shuquge.com";

    @Override
    public RequestItem handle(SpiderPage page) throws Exception {
        WebClient webClient = page.getWebClient();
        HtmlPage htmlPage = webClient.getPage(page.getUrl());
        processNovelSubject(htmlPage);
        chapters.addAll(processNovelDirectory(htmlPage));

        //获取页数
        HtmlPage nextPage;

        String nextPageLink = ((DomElement) htmlPage.getFirstByXPath("//span[@class='right']/a")).getAttribute("href");
        while (StringUtils.isNotEmpty(nextPageLink)) {
            nextPage = webClient.getPage(HOST + nextPageLink);
            chapters.addAll(processNovelDirectory(nextPage));
            nextPageLink = ((DomElement) nextPage.getFirstByXPath("//span[@class='right']/a")).getAttribute("href");
        }
        System.out.println("setsize:" + chapters.size());
        return null;
    }

    //处理小说目录索引
    private Set<String> processNovelDirectory(HtmlPage page) {
        List<DomElement> list = page.getByXPath("//ul[@class='chapter']/following-sibling::ul[@class='chapter']/li");
        Set<String> chapterLinks = new HashSet<>();
        for (DomElement doc : list) {
            String chapterLink = ((DomElement) doc.getFirstByXPath("a")).getAttribute("href");
            String title = ((DomElement) doc.getFirstByXPath("a")).getTextContent();
            System.out.println(title + ":" + chapterLink);
            chapterLinks.add(chapterLink);
        }
        return chapterLinks;
    }

    //处理小说书名，作者，简介，状态等信息
    public void processNovelSubject(HtmlPage page) {
        DomElement domElement = page.getFirstByXPath("//div[@class='block']");
        String image = ((DomElement) domElement.getFirstByXPath("div[@class='block_img2']/img")).getAttribute("src");
        String title = ((DomElement) domElement.getFirstByXPath("//h2")).getTextContent();
        String author = ((DomElement) domElement.getFirstByXPath("div[@class='block_txt2']/p[2]")).getTextContent();
        String status = ((DomElement) domElement.getFirstByXPath("div[@class='block_txt2']/p[4]")).getTextContent();
        String lastUpdate = ((DomElement) domElement.getFirstByXPath("div[@class='block_txt2']/p[5]")).getTextContent();
        String introduce = ((DomElement) domElement.getFirstByXPath("//div[@class='intro_info']")).getTextContent();
        System.out.println(String.format("Title:%s, Image:%s, Author:%s", title, image, introduce));
    }

    @Override
    public Queue getRequestQueue() {
        return requests;
    }
}