package com.achui.crawler.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WebDriverWaiting {

    public static void wait4element(WebDriver webDriver, Duration timeout, By selector) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
        wait.withTimeout(timeout)
                .ignoring(NoSuchElementException.class)
                .pollingEvery(Duration.ofMillis(500))
                .withMessage("Demo");
        WebElement processBar = null;
        try {
            processBar = wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(selector);
                }
            });
        } catch (Exception e) {
        }
        if (processBar != null) {
            WebDriverWait waitProcessor = new WebDriverWait(webDriver, 60);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
        }
    }
}
