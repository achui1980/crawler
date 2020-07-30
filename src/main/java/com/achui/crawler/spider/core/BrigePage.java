package com.achui.crawler.spider.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author portz
 * @date 2020/7/29 17:31
 */
public class BrigePage extends Page {

    public BrigePage (WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String processPage() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, 30);
        WebElement tableMenu = webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/div/ul/div[9]/li")));
        tableMenu.click();
        WebElement table = webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div/div[1]/div/ul/div[9]/li/ul/div[4]/a/li")));
        table.click();
        WebElement searchBtn = webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[1]/button[1]")));
        WebElement searchInput = webDriverWait.until(d -> d.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[1]/div[1]/input")));
        searchInput.sendKeys("F");
        searchBtn.click();
        return null;
    }
}
