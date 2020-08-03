package com.achui.crawler.spider.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author portz
 * @date 2020/7/29 17:38
 */
public class SearchPage extends Page {

    public SearchPage (WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String processPage() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, 30);
        WebElement mask = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[5]"));
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(mask)));
        WebElement nextPage = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[3]/div/button[2]"));
        while (nextPage.getAttribute("disabled") == null) {
            //TODO: Business logic
            processLogic();
            nextPage.click();
            mask = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[5]"));
            webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(mask)));
        }
        //Process last page;
        processLogic();
        return null;
    }
    private String processLogic() {
        WebElement titleElement = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/section/div/div[2]/div[3]/table/tbody/tr[1]/td[3]/div/span[1]"));
        System.out.println(titleElement.getText());
        return null;
    }
}
