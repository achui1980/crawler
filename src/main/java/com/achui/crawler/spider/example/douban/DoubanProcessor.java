package com.achui.crawler.spider.example.douban;

import com.achui.crawler.spider.core.PageProcessor;
import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class DoubanProcessor implements PageProcessor {
    private Queue requests = Lists.newLinkedList();

    @Override
    public void login(SpiderPage page) throws Exception {

    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebDriver webDriver = page.getWebDriverEngine();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);
        WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[2]/span[3]"));
        WebElement currentPage = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/ol/li[1]"));
        List<WebElement> filmList = nextPage.findElements(By.xpath("//*[@id=\"content\"]/div/div[1]/ol/li"));
        List<WebElement> aLink = nextPage.findElements(By.xpath("a"));
        while (aLink.size() > 0) {
            TimeUnit.SECONDS.sleep(1);
            for (WebElement element : filmList) {
                String title = element.findElement(By.xpath("div/div[2]/div[1]/a/span[1]")).getText();
                String rate = element.findElement(By.xpath("div/div[2]/div[2]/div/span[2]")).getText();
                RequestItem requestItem = new RequestItem();
                requestItem.put("title", title);
                requestItem.put("rate", rate);
                System.out.println(title + ":" + rate);
                requests.offer(requestItem);
            }
            //TODO: Business logic
            nextPage.findElement(By.xpath("a")).click();
            nextPage = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[2]/span[3]"));
            aLink = nextPage.findElements(By.xpath("a"));
            filmList = nextPage.findElements(By.xpath("//*[@id=\"content\"]/div/div[1]/ol/li"));
        }

    }

    @Override
    public Queue getRequestQueue() {
        return requests;
    }
}
