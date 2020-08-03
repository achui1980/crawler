package com.achui.crawler.spider.core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * @author portz
 * @date 2020/7/31 15:36
 */
@Slf4j
public class SeleniumDownloader {

    public ChromeDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "conf/chromedriver_v84.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
