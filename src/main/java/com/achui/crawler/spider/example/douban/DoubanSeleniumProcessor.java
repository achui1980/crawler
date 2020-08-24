package com.achui.crawler.spider.example.douban;

import com.achui.crawler.spider.core.AbstractPageProcessor;
import com.achui.crawler.spider.core.RequestItem;
import com.achui.crawler.spider.core.SpiderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DoubanSeleniumProcessor extends AbstractPageProcessor {

    @Override
    public void login(SpiderPage page) throws Exception {

    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebDriver webDriver = page.getWebDriverEngine();
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[2]/span[3]"));
            List<WebElement> filmList = nextPage.findElements(By.xpath("//*[@id=\"content\"]/div/div[1]/ol/li"));
            for (WebElement element : filmList) {
                String title = element.findElement(By.xpath("div/div[2]/div[1]/a/span[1]")).getText();
                String rate = element.findElement(By.xpath("div/div[2]/div[2]/div/span[2]")).getText();
                RequestItem requestItem = new RequestItem();
                requestItem.put("title", title);
                requestItem.put("rate", rate);
                System.out.println(title + ":" + rate);
                getRequestQueue().offer(requestItem);
            }
            //TODO: Business logic
            List<WebElement> aLink = nextPage.findElements(By.xpath("a"));
            if (aLink.size() == 0) {
                break;
            }
            nextPage.findElement(By.xpath("a")).click();
        }

    }
}
